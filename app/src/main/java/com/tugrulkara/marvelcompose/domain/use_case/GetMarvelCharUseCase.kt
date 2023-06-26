package com.tugrulkara.marvelcompose.domain.use_case

import com.tugrulkara.marvelcompose.data.remote.dto.toCharList
import com.tugrulkara.marvelcompose.domain.model.MarvelChar
import com.tugrulkara.marvelcompose.domain.repository.MarvelRepository
import com.tugrulkara.marvelcompose.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetMarvelCharUseCase @Inject constructor(private val repository: MarvelRepository) {

    fun executeGetMarvelChar() : Flow<Resource<List<MarvelChar>>> = flow{

        try {
            emit(Resource.Loading())
            val marvelChar = repository.getMarvelChar()
            if (marvelChar.status.equals("Ok")){
                emit(Resource.Success(marvelChar.toCharList()))
            }else{
                emit(Resource.Error("No Movie Found"))
            }
        }catch (e:Exception){
            emit(Resource.Error("Error"))
        }

    }

    fun executeGetCopyrigth() : Flow<String> = flow{

        try {
            val marvelCharDto = repository.getMarvelChar()
            if (marvelCharDto.status.equals("Ok")){
                emit(marvelCharDto.copyright)
            }
        }catch (e:Exception){
            e.printStackTrace()
        }

    }

}