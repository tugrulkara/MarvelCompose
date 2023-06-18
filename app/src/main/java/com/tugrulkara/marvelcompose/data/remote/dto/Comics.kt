package com.tugrulkara.marvelcompose.data.remote.dto

data class Comics(
    val available: String,
    val collectionURI: String,
    val items: List<Item>,
    val returned: String
)