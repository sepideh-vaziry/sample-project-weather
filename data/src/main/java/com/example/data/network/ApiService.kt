package com.example.data.network

import com.example.data.entity.OneCallEntity
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiService {

    @GET("onecall")
    suspend fun getAllWeatherInfo(
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double,
        @Query("units") units: String,
        @Query("appid") appId: String
    ) : OneCallEntity

}