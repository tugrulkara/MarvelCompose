package com.tugrulkara.marvelcompose.presentation

sealed class Screen(val root:String) {

    object MarvelCharScreen : Screen("marvel_char_screen")
    object MarvelSeriesScreen : Screen("marvel_series_screen")
}
