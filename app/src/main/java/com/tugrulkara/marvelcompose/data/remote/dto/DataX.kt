package com.tugrulkara.marvelcompose.data.remote.dto

data class DataX(
    val count: String,
    val limit: String,
    val offset: String,
    val results: List<ResultX>,
    val total: String
)