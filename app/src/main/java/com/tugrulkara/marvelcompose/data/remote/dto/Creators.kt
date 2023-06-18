package com.tugrulkara.marvelcompose.data.remote.dto

data class Creators(
    val available: String,
    val collectionURI: String,
    val items: List<ItemX>,
    val returned: String
)