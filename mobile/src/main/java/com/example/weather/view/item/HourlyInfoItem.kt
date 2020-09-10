package com.example.weather.view.item

import android.content.Context
import android.view.View
import com.example.presentation.model.InfoModel
import com.example.presentation.model.WeatherModel
import com.example.weather.R
import com.example.weather.view.item.base.BaseItem
import kotlinx.android.synthetic.main.item_hourly_info.view.*
import java.util.*

class HourlyInfoItem(itemView: View) : BaseItem<InfoModel>(itemView) {

    override fun bind(context: Context, contentObject: InfoModel?) {
        if (contentObject == null) {
            return
        }

        itemView.textTemp.text = String.format(
            context.resources.getString(R.string.hourly_info_item_temp_format),
            contentObject.temp.toInt()
        )
        
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = contentObject.dt * 1000

        val amPm = if (calendar.get(Calendar.AM_PM) == Calendar.AM) {
            context.resources.getString(R.string.hourly_info_item_am)
        }
        else {
            context.resources.getString(R.string.hourly_info_item_pm)
        }
        
        itemView.textTime.text = String.format(
            context.resources.getString(R.string.hourly_info_item_time_format),
            calendar.get(Calendar.HOUR),
            amPm
        )

        if (!contentObject.weather.isNullOrEmpty()) {
            setWeatherImage(contentObject.weather!![0], calendar.get(Calendar.HOUR_OF_DAY))
        }
    }

    override fun loading(isVisible: Boolean) {
        //Nothing
    }
    
    //**********************************************************************************************
    private fun setWeatherImage(weatherModel: WeatherModel, weatherHour: Int) {

        when (weatherModel.id / 100) {
            2L -> itemView.imageWeather.setImageResource(R.drawable.ic_thunderstorm)

            3L -> itemView.imageWeather.setImageResource(R.drawable.ic_mist)

            5L -> itemView.imageWeather.setImageResource(R.drawable.ic_rain)

            6L -> itemView.imageWeather.setImageResource(R.drawable.ic_snow)

            7L -> itemView.imageWeather.setImageResource(R.drawable.ic_mist)

            8L -> if (weatherModel.id.rem(100) == 0L) {
                when {
                    weatherHour < 7 -> {
                        itemView.imageWeather.setImageResource(R.drawable.ic_clear_sky_dawn)
                    }
                    weatherHour < 12 -> {
                        itemView.imageWeather.setImageResource(R.drawable.ic_clear_sky_morning)
                    }
                    weatherHour < 17 -> {
                        itemView.imageWeather.setImageResource(R.drawable.ic_clear_sky_morning)
                    }
                    weatherHour < 20 -> {
                        itemView.imageWeather.setImageResource(R.drawable.ic_clear_sky_evening)
                    }
                    else -> {
                        itemView.imageWeather.setImageResource(R.drawable.ic_clear_sky_night)
                    }
                }
            }
            else {
                when {
                    weatherHour < 7 -> {
                        itemView.imageWeather.setImageResource(R.drawable.ic_few_cloud_dawn)
                    }
                    weatherHour < 12 -> {
                        itemView.imageWeather.setImageResource(R.drawable.ic_few_cloud_morning)
                    }
                    weatherHour < 17 -> {
                        itemView.imageWeather.setImageResource(R.drawable.ic_few_cloud_morning)
                    }
                    weatherHour < 20 -> {
                        itemView.imageWeather.setImageResource(R.drawable.ic_few_cloud_evening)
                    }
                    else -> {
                        itemView.imageWeather.setImageResource(R.drawable.ic_few_clound_night)
                    }
                }
            }

        }
        
    }
    
}