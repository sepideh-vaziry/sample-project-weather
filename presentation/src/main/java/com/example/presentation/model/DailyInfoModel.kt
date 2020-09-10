package com.example.presentation.model

class DailyInfoModel {

    var dt: Long = 0
    var temp: TempModel? = null
    var pressure: Int = 0
    var humidity: Int = 0
    var weather: List<WeatherModel>? = null

}