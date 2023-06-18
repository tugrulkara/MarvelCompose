package com.tugrulkara.marvelcompose.data.remote.dto

import com.tugrulkara.marvelcompose.domain.model.MarvelSeries

data class MarvelSeriesDto(
    val attributionHTML: String,
    val attributionText: String,
    val code: String,
    val copyright: String,
    val `data`: DataX,
    val etag: String,
    val status: String
)
fun MarvelSeriesDto.toSerieList(): List<MarvelSeries>{
    return data.results.map {result -> MarvelSeries(
        id = result.id, thumbnail = result.thumbnail.path, title = result.title, urls = result.urls) }
}