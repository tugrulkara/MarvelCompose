package com.tugrulkara.marvelcompose.data.remote.dto

import com.tugrulkara.marvelcompose.domain.model.MarvelChar

data class MarvelCharDto(
    val attributionHTML: String,
    val attributionText: String,
    val code: String,
    val copyright: String,
    val `data`: Data,
    val etag: String,
    val status: String
)

fun MarvelCharDto.toCharList() : List<MarvelChar> {
    return data.results.map {result -> MarvelChar(id = result.id, name = result.name, thumbnail = result.thumbnail.path) }
}