package com.tugrulkara.marvelcompose.presentation.marvel_char

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tugrulkara.marvelcompose.domain.model.MarvelChar
import com.tugrulkara.marvelcompose.domain.use_case.GetMarvelCharUseCase
import com.tugrulkara.marvelcompose.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MarvelCharViewModel @Inject constructor(private val useCase: GetMarvelCharUseCase):ViewModel() {

    private var _state= mutableStateOf(MarvelCharState())
    val state : State<MarvelCharState> =_state

    var charList= mutableStateOf<List<MarvelChar>>(listOf())

    private var initialCharList : List<MarvelChar> = emptyList()
    private var isSearchStarting = true

    var job : Job? = null

    init {
        getMarvelChars()
    }


    fun searchCharList(query: String) {
        val listToSearch = if(isSearchStarting) {
            charList.value
        } else {
            initialCharList
        }
        viewModelScope.launch(Dispatchers.Default) {
            if(query.isEmpty()) {
                charList.value = initialCharList
                isSearchStarting = true
                return@launch
            }
            val results = listToSearch.filter {
                it.name.contains(query.trim(), ignoreCase = true)
            }
            if(isSearchStarting) {
                initialCharList = charList.value
                isSearchStarting = false
            }
            charList.value = results
        }
    }

    fun getMarvelChars(){
        job?.cancel()

        _state.value=MarvelCharState(isLoading = true)
        job=useCase.executeGetMarvelChar().onEach {
            when(it){
                is Resource.Success ->{
                    it.data?.let {listChar->
                        charList.value=listChar
                        _state.value=MarvelCharState(isLoading = false)
                        _state.value=MarvelCharState(errorMessage = "")
                    }

                    useCase.executeGetCopyrigth().onEach {copyright->
                        _state.value=MarvelCharState(copyright = copyright)
                    }.launchIn(viewModelScope)

                }
                is Resource.Error ->{
                    _state.value=MarvelCharState(isLoading = false)
                    _state.value=MarvelCharState(errorMessage = it.message.toString())
                }
                is Resource.Loading->{
                    _state.value=MarvelCharState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)

    }

}