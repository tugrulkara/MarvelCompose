package com.tugrulkara.marvelcompose.data.repository

import com.tugrulkara.marvelcompose.data.remote.MarvelAPI
import com.tugrulkara.marvelcompose.data.remote.dto.MarvelCharDto
import com.tugrulkara.marvelcompose.data.remote.dto.MarvelSeriesDto
import com.tugrulkara.marvelcompose.domain.repository.MarvelRepository
import javax.inject.Inject

class MarvelRepositoryImpl @Inject constructor(private val api:MarvelAPI) : MarvelRepository {
    override suspend fun getMarvelChar(): MarvelCharDto {
        return api.getMarvelChar()
    }

    override suspend fun getMarvelSeries(characterId: String): MarvelSeriesDto {
        return api.getMarvelCharSeries(characterId)
    }

}