package com.example.presentation.model

import org.json.JSONObject
import java.io.Serializable

class CityModel(jsonObject: JSONObject) : Serializable {

    companion object {
        private const val KEY_ID = "id"
        private const val KEY_NAME = "name"
        private const val KEY_COUNTRY = "country"
        private const val KEY_COORDINATE = "coord"
    }

    var id: Long = 0
    var name: String? = null
    var country: String? = null
    var coordinate: CoordinateModel? = null

    //**********************************************************************************************
    init {
        if (jsonObject.has(KEY_ID)) {
            id = jsonObject.getLong(KEY_ID)
        }

        if (jsonObject.has(KEY_NAME)) {
            name = jsonObject.getString(KEY_NAME)
        }

        if (jsonObject.has(KEY_COUNTRY)) {
            country = jsonObject.getString(KEY_COUNTRY)
        }

        if (jsonObject.has(KEY_COORDINATE)) {
            val coordinateJsonObject = jsonObject.getJSONObject(KEY_COORDINATE)

            coordinate = CoordinateModel(coordinateJsonObject)
        }
    }

}