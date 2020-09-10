package com.example.data.entity.mapper

import com.example.data.entity.WeatherEntity
import com.example.domain.entity.Weather
import javax.inject.Inject

class WeatherEntityDataMapper @Inject constructor() {

    /**
     * Transform list of [WeatherEntity] into list of [Weather]
     *
     * @param weatherEntityList List of object will be transformed.
     * @return List of [Weather] if valid [WeatherEntity] otherwise null.
     */
    fun transform(weatherEntityList: List<WeatherEntity>?) : List<Weather> {
        val weatherList: MutableList<Weather> = ArrayList()

        if (weatherEntityList == null) {
            return weatherList
        }

        for (weatherEntity in weatherEntityList) {
            val weather = transform(weatherEntity)

            if (weather != null) {
                weatherList.add(weather)
            }
        }

        return weatherList
    }

    /**
     * Transform a [weatherEntity] into an [Weather]
     *
     * @param weatherEntity Object to be transformed.
     * @return [Weather] if valid [weatherEntity] otherwise null.
     */
    fun transform(weatherEntity: WeatherEntity?): Weather? {
        if (weatherEntity == null) {
            return null
        }

        val weather = Weather()

        weather.id = weatherEntity.id
        weather.main = weatherEntity.main
        weather.description = weatherEntity.description

        return weather
    }
    
}