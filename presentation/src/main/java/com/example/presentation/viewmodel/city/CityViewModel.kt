package com.example.presentation.viewmodel.city

import android.content.SharedPreferences
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.example.presentation.model.CityModel
import com.example.presentation.model.CoordinateModel
import com.example.presentation.viewmodel.base.getDouble
import com.example.presentation.viewmodel.base.putDouble
import org.json.JSONObject

class CityViewModel @ViewModelInject constructor(
    private val sharedPreferences: SharedPreferences
) : ViewModel() {

    companion object {
        private const val KEY_ID = "id"
        private const val KEY_NAME = "name"
        private const val KEY_LATITUDE = "lat"
        private const val KEY_LONGITUDE = "lon"
    }

    //**********************************************************************************************
    fun setSelectedCity(cityModel: CityModel) {
        sharedPreferences.edit().apply {
            putLong(KEY_ID, cityModel.id)
            putString(KEY_NAME, cityModel.name)
            putDouble(KEY_LATITUDE, cityModel.coordinate?.latitude ?: 0.0)
            putDouble(KEY_LONGITUDE, cityModel.coordinate?.longitude ?: 0.0)
        }.apply()
    }

    //**********************************************************************************************
    fun getSelectedCity() : CityModel? {
        if (!sharedPreferences.contains(KEY_ID)) {
            return null
        }

        return CityModel(JSONObject()).apply {
            id = sharedPreferences.getLong(KEY_ID, 0)
            name = sharedPreferences.getString(KEY_NAME, null)
            coordinate = CoordinateModel(JSONObject()).apply {
                latitude = sharedPreferences.getDouble(KEY_LATITUDE, 0.0)
                longitude = sharedPreferences.getDouble(KEY_LONGITUDE, 0.0)
            }
        }
    }

}