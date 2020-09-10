package com.example.domain.entity

class DailyInfo {

    var dt: Long = 0
    var temp: Temp? = null
    var pressure: Int = 0
    var humidity: Int = 0
    var weather: List<Weather>? = null

}