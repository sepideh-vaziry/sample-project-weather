package com.example.presentation.model

import org.json.JSONObject
import java.io.Serializable

class CoordinateModel(jsonObject: JSONObject) : Serializable {

    companion object {
        private const val KEY_LATITUDE = "lat"
        private const val KEY_LONGITUDE = "lon"
    }

    var latitude: Double = 0.0
    var longitude: Double = 0.0

    init {
        if (jsonObject.has(KEY_LATITUDE)) {
            latitude = jsonObject.getDouble(KEY_LATITUDE)
        }

        if (jsonObject.has(KEY_LONGITUDE)) {
            longitude = jsonObject.getDouble(KEY_LONGITUDE)
        }
    }

}