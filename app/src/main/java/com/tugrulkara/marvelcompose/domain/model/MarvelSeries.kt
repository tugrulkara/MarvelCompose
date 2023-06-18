package com.tugrulkara.marvelcompose.domain.model

import com.tugrulkara.marvelcompose.data.remote.dto.UrlX

data class MarvelSeries(
    val id: String,
    val thumbnail: String,
    val title: String,
    val urls: List<UrlX>
)
