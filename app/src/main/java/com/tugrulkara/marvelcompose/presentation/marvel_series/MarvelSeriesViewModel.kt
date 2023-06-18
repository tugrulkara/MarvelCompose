package com.tugrulkara.marvelcompose.presentation.marvel_series

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tugrulkara.marvelcompose.domain.use_case.GetMarvelSeriesUseCase
import com.tugrulkara.marvelcompose.util.Constants.CHAR_ID
import com.tugrulkara.marvelcompose.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MarvelSeriesViewModel @Inject constructor(
    val useCase: GetMarvelSeriesUseCase,
    private val stateHandle: SavedStateHandle):ViewModel() {

    private val _state= mutableStateOf(MarvelSeriesState())
    val state:State<MarvelSeriesState> = _state

    var job : Job? = null

    init {
        stateHandle.get<String>(CHAR_ID)?.let {
            println(it)
            getMarvelSeries(it)
            //getMarvelSeries("1017100")
        }
    }

    fun getMarvelSeries(characterId:String){
        job?.cancel()

        job=useCase.executeGetMarvelSeries(characterId).onEach {

            when(it){
                is Resource.Success->{
                    _state.value= MarvelSeriesState(marvelSeriesList = it.data ?: emptyList())
                }
                is Resource.Error ->{
                    _state.value=MarvelSeriesState(errorMessage = "Error")
                }
                is Resource.Loading->{
                    _state.value=MarvelSeriesState(isLoading = true)
                }
            }

        }.launchIn(viewModelScope)

    }
}