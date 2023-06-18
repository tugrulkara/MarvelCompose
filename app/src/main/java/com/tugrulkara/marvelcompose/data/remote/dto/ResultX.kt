package com.tugrulkara.marvelcompose.data.remote.dto

data class ResultX(
    val characters: Characters,
    val comics: ComicsX,
    val creators: Creators,
    val description: String,
    val endYear: String,
    val events: EventsX,
    val id: String,
    val modified: String,
    val next: Next,
    val previous: Previous,
    val rating: String,
    val resourceURI: String,
    val startYear: String,
    val stories: StoriesX,
    val thumbnail: ThumbnailX,
    val title: String,
    val urls: List<UrlX>
)