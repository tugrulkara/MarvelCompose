package com.tugrulkara.marvelcompose.data.remote.dto

data class StoriesX(
    val available: String,
    val collectionURI: String,
    val items: List<ItemEvent>,
    val returned: String
)