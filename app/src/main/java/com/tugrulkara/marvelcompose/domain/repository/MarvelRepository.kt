package com.tugrulkara.marvelcompose.domain.repository

import com.tugrulkara.marvelcompose.data.remote.dto.MarvelCharDto
import com.tugrulkara.marvelcompose.data.remote.dto.MarvelSeriesDto


interface MarvelRepository  {

    suspend fun getMarvelChar() : MarvelCharDto
    suspend fun getMarvelSeries(characterId:String) : MarvelSeriesDto
}