package com.tugrulkara.marvelcompose.domain.model


import com.tugrulkara.marvelcompose.data.remote.dto.Thumbnail

data class MarvelChar(
    val id: String,
    val name: String,
    val thumbnail: Thumbnail,
)
