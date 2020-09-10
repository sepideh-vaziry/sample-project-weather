package com.example.weather.view.item

import android.content.Context
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.example.presentation.model.DailyInfoModel
import com.example.presentation.model.WeatherModel
import com.example.weather.R
import com.example.weather.view.item.base.BaseItem
import kotlinx.android.synthetic.main.item_daily_info.view.*
import java.util.*

class DailyInfoItem(itemView: View) : BaseItem<DailyInfoModel>(itemView) {

    override fun bind(context: Context, contentObject: DailyInfoModel?) {
        if (contentObject == null) {
            return
        }

        val calendar = Calendar.getInstance()
        calendar.timeInMillis = contentObject.dt * 1000

        val currentCalendar = Calendar.getInstance()
        currentCalendar.timeInMillis = System.currentTimeMillis()

        itemView.textDayName.text = if (
            calendar.get(Calendar.DAY_OF_MONTH) == (currentCalendar.get(Calendar.DAY_OF_MONTH) + 1)
        ) {
            context.resources.getString(R.string.hourly_info_item_tomorrow)
        }
        else {
            calendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.getDefault())
        }

        contentObject.temp?.let {
            val secondaryTextColor = ContextCompat.getColor(context, R.color.secondaryTextColor)

            val textMaxMin = String.format(
                context.resources.getString(R.string.daily_info_item_temp_format),
                it.max.toInt(),
                it.min.toInt()
            )

            val spannable = SpannableString(textMaxMin)
            spannable.setSpan(
                ForegroundColorSpan(secondaryTextColor),
                it.max.toInt().toString().length,
                textMaxMin.length,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
            )

            itemView.textMinMaxTemp.setText(
                spannable,
                TextView.BufferType.SPANNABLE
            )
        }

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