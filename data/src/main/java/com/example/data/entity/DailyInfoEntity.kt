package com.example.data.entity

import java.io.Serializable

class DailyInfoEntity : Serializable {

    var dt: Long = 0
    var temp: TempEntity? = null
    var pressure: Int = 0
    var humidity: Int = 0
    var weather: List<WeatherEntity>? = null

}