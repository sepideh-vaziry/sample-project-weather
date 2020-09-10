package com.example.presentation.model.mapper

import com.example.domain.entity.Weather
import com.example.presentation.model.WeatherModel
import javax.inject.Inject

class WeatherModelDataMapper @Inject constructor() {

    /**
     * Transform list of [Weather] into list of [WeatherModel]
     *
     * @param weatherList List of object will be transformed.
     * @return List of [WeatherModel] if valid [Weather] otherwise null.
     */
    fun transform(weatherList: List<Weather>?) : List<WeatherModel> {
        val weatherModelList: MutableList<WeatherModel> = ArrayList()

        if (weatherList == null) {
            return weatherModelList
        }

        for (weather in weatherList) {
            val weatherModel = transform(weather)

            if (weatherModel != null) {
                weatherModelList.add(weatherModel)
            }
        }

        return weatherModelList
    }

    /**
     * Transform a [weather] into an [WeatherModel]
     *
     * @param weather Object to be transformed.
     * @return [WeatherModel] if valid [weather] otherwise null.
     */
    fun transform(weather: Weather?): WeatherModel? {
        if (weather == null) {
            return null
        }

        val weatherModel = WeatherModel()

        weatherModel.id = weather.id
        weatherModel.main = weather.main
        weatherModel.description = weather.description

        return weatherModel
    }
    
}