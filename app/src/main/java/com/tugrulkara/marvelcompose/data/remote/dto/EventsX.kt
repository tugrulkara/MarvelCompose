package com.tugrulkara.marvelcompose.data.remote.dto

data class EventsX(
    val available: String,
    val collectionURI: String,
    val items: List<ItemEvent>,
    val returned: String
)