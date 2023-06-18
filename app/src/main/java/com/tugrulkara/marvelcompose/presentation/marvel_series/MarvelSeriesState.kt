package com.tugrulkara.marvelcompose.presentation.marvel_series

import com.tugrulkara.marvelcompose.domain.model.MarvelChar
import com.tugrulkara.marvelcompose.domain.model.MarvelSeries

data class MarvelSeriesState(
    val isLoading:Boolean=false,
    val marvelSeriesList: List<MarvelSeries> = emptyList(),
    val errorMessage:String=""
)
