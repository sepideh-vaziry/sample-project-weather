package com.example.data.entity

import java.io.Serializable

class InfoEntity : Serializable {

    var dt: Long = 0
    var temp: Float = 0F
    var pressure: Int = 0
    var humidity: Int = 0
    var weather: List<WeatherEntity>? = null

}