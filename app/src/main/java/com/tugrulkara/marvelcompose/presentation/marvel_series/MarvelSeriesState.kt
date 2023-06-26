package com.tugrulkara.marvelcompose.presentation.marvel_series

import com.tugrulkara.marvelcompose.domain.model.MarvelSeries

data class MarvelSeriesState(
    var isLoading:Boolean=false,
    var marvelSeriesList: List<MarvelSeries> = emptyList(),
    var errorMessage:String=""
)
