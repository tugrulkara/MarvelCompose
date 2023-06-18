package com.tugrulkara.marvelcompose.domain.use_case

import com.tugrulkara.marvelcompose.data.remote.dto.toSerieList
import com.tugrulkara.marvelcompose.domain.model.MarvelSeries
import com.tugrulkara.marvelcompose.domain.repository.MarvelRepository
import com.tugrulkara.marvelcompose.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetMarvelSeriesUseCase @Inject constructor(private val repository: MarvelRepository) {

    fun executeGetMarvelSeries(characterId:String): Flow<Resource<List<MarvelSeries>>> = flow {

        try {
            emit(Resource.Loading())
            val marvelSeries=repository.getMarvelSeries(characterId)
            if (marvelSeries.status.equals("Ok")){
                emit(Resource.Success(marvelSeries.toSerieList()))
            }else{
                emit(Resource.Error("No Data"))
            }

        }catch (e:Exception){
            emit(Resource.Error("Error"))
        }

    }
}