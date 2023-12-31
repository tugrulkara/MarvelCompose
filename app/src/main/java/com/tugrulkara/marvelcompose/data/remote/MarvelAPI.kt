package com.tugrulkara.marvelcompose.data.remote

import com.tugrulkara.marvelcompose.data.remote.dto.MarvelCharDto
import com.tugrulkara.marvelcompose.data.remote.dto.MarvelSeriesDto
import com.tugrulkara.marvelcompose.util.Constants.HASH
import com.tugrulkara.marvelcompose.util.Constants.PUBLIC_KEY
import com.tugrulkara.marvelcompose.util.Constants.TIME_STAMP
import com.tugrulkara.marvelcompose.util.toHex
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MarvelAPI {

    @GET("/v1/public/characters")
    suspend fun getMarvelChar(
        @Query("series") series:String= "18455, 335, 24323, 758, 23768, 24229, 31494",
        @Query("orderBy") orderBy:String="-name",
        @Query("limit") limit: Int=100,
        @Query("ts") timeStamp : String= TIME_STAMP.toString(),
        @Query("apikey") apiKey : String=PUBLIC_KEY,
        @Query("hash") hashKey : String= HASH.toHex()
    ):MarvelCharDto

    @GET("v1/public/characters/{characterId}/series")
    suspend fun getMarvelCharSeries(
        @Path("characterId") characterId :String,
        @Query("orderBy") orderBy:String="startYear",
        @Query("ts") timeStamp : String= TIME_STAMP.toString(),
        @Query("apikey") apiKey : String=PUBLIC_KEY,
        @Query("hash") hashKey : String= HASH.toHex()
    ):MarvelSeriesDto


}