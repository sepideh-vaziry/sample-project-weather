package com.example.presentation.viewmodel.city

import android.app.Application
import android.content.Context
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.presentation.model.CityModel
import com.example.presentation.viewmodel.base.DataWrapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONArray
import timber.log.Timber
import java.io.IOException
import java.io.InputStream
import java.nio.charset.Charset

class CityListViewModel @ViewModelInject constructor(
    application: Application
) : AndroidViewModel(application) {

    companion object {
        private val TAG = CityListViewModel::class.java.simpleName

        private const val CITY_LIST_JSON_FILE_NAME = "city.list.json"
        private const val KEY_COUNTRY_CODE_IRAN = "IR"

    }

    private val mApplication = application

    private var mCityListLiveData: MutableLiveData<DataWrapper<List<CityModel>>>? = null

    //**********************************************************************************************
    fun getCityList() : LiveData<DataWrapper<List<CityModel>>> {

        if (mCityListLiveData == null) {
            mCityListLiveData = MutableLiveData()
            mCityListLiveData!!.value = DataWrapper.loading()

            viewModelScope.launch {
                val cityList = ArrayList<CityModel>()

                withContext(Dispatchers.IO) {
                    val jsonArray = JSONArray(
                        getJSONString(mApplication, CITY_LIST_JSON_FILE_NAME)
                    )

                    for (index in 0 until (jsonArray.length())) {
                        val city = CityModel(jsonArray.getJSONObject(index))

                        if (city.country == KEY_COUNTRY_CODE_IRAN) {
                            cityList.add(city)
                        }
                    }
                }

                mCityListLiveData!!.value = DataWrapper.success(cityList)
            }

        }


        return mCityListLiveData!!
    }

    //******************************************************************************************************************
    /**
     * Load game json from the assets.
     */
    private fun getJSONString(context: Context, jsonFileName: String): String? {
        var jsonString: String? = null

        try {
            val inputStream: InputStream = context.assets.open(jsonFileName)
            val size: Int = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()

            jsonString = String(buffer, Charset.forName("UTF-8"))
        }
        catch (e: IOException) {
            Timber.tag(TAG).e(e)
        }

        return jsonString
    }

}