package com.example.data.entity

import java.io.Serializable

class OneCallEntity : Serializable {

    var current: InfoEntity? = null
    var hourly: List<InfoEntity>? = null
    var daily: List<DailyInfoEntity>? = null

}