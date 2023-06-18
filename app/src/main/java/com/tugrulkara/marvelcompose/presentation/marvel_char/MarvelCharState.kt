package com.tugrulkara.marvelcompose.presentation.marvel_char

import com.tugrulkara.marvelcompose.domain.model.MarvelChar

data class MarvelCharState(
    val isLoading:Boolean=false,
    val marvelCharList: List<MarvelChar> = emptyList(),
    val errorMessage:String=""
)
