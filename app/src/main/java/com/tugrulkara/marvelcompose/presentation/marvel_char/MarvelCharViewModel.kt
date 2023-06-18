package com.tugrulkara.marvelcompose.presentation.marvel_char

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tugrulkara.marvelcompose.domain.model.MarvelChar
import com.tugrulkara.marvelcompose.domain.use_case.GetMarvelCharUseCase
import com.tugrulkara.marvelcompose.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MarvelCharViewModel @Inject constructor(private val useCase: GetMarvelCharUseCase):ViewModel() {

    private val _state= mutableStateOf(MarvelCharState())
    val state : State<MarvelCharState> =_state

    var job : Job? = null

    init {
        getMarvelChars()
    }

    private fun getMarvelChars(){
        job?.cancel()

        job=useCase.executeGetMarvelChar().onEach {
            when(it){
                is Resource.Success ->{
                    _state.value=MarvelCharState(marvelCharList = it.data ?: emptyList())
                }
                is Resource.Error ->{
                    _state.value=MarvelCharState(errorMessage = "error")
                }
                is Resource.Loading->{
                    _state.value=MarvelCharState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

}