package com.tugrulkara.marvelcompose.presentation.marvel_char


data class MarvelCharState(
    var isLoading:Boolean=false,
    var errorMessage:String="",
    val copyright:String=""
)
