package com.example.domain.repository

import com.example.domain.entity.OneCall

abstract class WeatherRepository : Repository() {

    abstract suspend fun getAllWeatherInfo(
        latitude: Double,
        longitude: Double
    ) : OneCall?

}