package com.example.data.repository

import com.example.data.entity.mapper.OneCallEntityDataMapper
import com.example.data.network.ApiService
import com.example.data.network.ServerInfo
import com.example.domain.entity.OneCall
import com.example.domain.repository.WeatherRepository
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val oneCallEntityDataMapper: OneCallEntityDataMapper
) : WeatherRepository() {

    companion object {
        private const val TEMP_UNIT = "metric"
    }

    //**********************************************************************************************
    override suspend fun getAllWeatherInfo(
        latitude: Double,
        longitude: Double
    ): OneCall? {
        val oneCallEntity = apiService.getAllWeatherInfo(
            latitude,
            longitude,
            TEMP_UNIT,
            ServerInfo.APP_ID
        )

        return oneCallEntityDataMapper.transform(oneCallEntity)
    }

}