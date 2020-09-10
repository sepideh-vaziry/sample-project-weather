package com.example.weather.view.activity.home

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.presentation.model.CityModel
import com.example.presentation.model.DailyInfoModel
import com.example.presentation.model.InfoModel
import com.example.presentation.model.OneCallModel
import com.example.presentation.viewmodel.base.Status
import com.example.presentation.viewmodel.city.CityViewModel
import com.example.presentation.viewmodel.weather.WeatherViewModel
import com.example.weather.R
import com.example.weather.base.hide
import com.example.weather.base.linearLayout
import com.example.weather.base.show
import com.example.weather.base.showToast
import com.example.weather.databinding.FragmentHomeBinding
import com.example.weather.view.activity.base.BaseFragment
import com.example.weather.view.adapter.DailyInfoAdapter
import com.example.weather.view.adapter.HourlyInfoAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_home.*
import timber.log.Timber
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList

@AndroidEntryPoint
class HomeFragment : BaseFragment() {

    companion object {
        private val TAG = HomeFragment::class.java.simpleName
    }

    @Inject
    lateinit var mHourlyInfoAdapter: HourlyInfoAdapter

    @Inject
    lateinit var mDailyInfoAdapter: DailyInfoAdapter

    private val mCityViewModel: CityViewModel by viewModels()
    private val mWeatherViewModel: WeatherViewModel by viewModels()

    private lateinit var mNavController: NavController

    private var mCityModel: CityModel? = null
    private var mOneCallModel: OneCallModel? = null

    private var mCurrentHour: Int = 0
    private var mStatusBarColor: Int = Color.WHITE

    private var mSelectedDayInfo: DailyInfoModel? = null

    //**********************************************************************************************
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentHomeBinding.inflate(
            inflater, container, false
        )

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mCityModel = mCityViewModel.getSelectedCity()

        mNavController = view.findNavController()

        if (mCityModel == null) {
            buttonLocation.text = resources.getString(R.string.home_button_location)
        }
        else {
            buttonLocation.text = mCityModel?.name
        }

        buttonLocation.setOnClickListener {
            mNavController.navigate(R.id.actionShowCityList)
        }

        //List config
        hourlyListConfig()
        dailyListConfig()

        //Get weather info
        changeBackgroundColor()
        loadWeatherData()

    }

    //**********************************************************************************************
    private fun hourlyListConfig() {
        recyclerViewHourlyInfo.apply {
            linearLayout(
                context,
                orientation = LinearLayoutManager.HORIZONTAL
            )

            adapter = mHourlyInfoAdapter
        }
    }

    //**********************************************************************************************
    private fun dailyListConfig() {
        mDailyInfoAdapter.setOnItemClickListener { position, dailyInfoModel ->

            if (dailyInfoModel != mSelectedDayInfo) {
                mSelectedDayInfo = dailyInfoModel
                mDailyInfoAdapter.setSelectedPosition(position)
            }
            else {
                mSelectedDayInfo = null
                mDailyInfoAdapter.setSelectedPosition(-1)
            }

            setCurrentTemp()
        }

        recyclerViewDailyInfo.apply {
            linearLayout(context)

            adapter = mDailyInfoAdapter
        }
    }

    //**********************************************************************************************
    private fun loadWeatherData() {
        if (mCityModel == null) {
            context?.showToast(resources.getString(R.string.home_location_error_message))
            return
        }

        mWeatherViewModel.getAllWeatherInfo(mCityModel!!)
            .observe(viewLifecycleOwner) { dataWrapper ->

                when (dataWrapper.status) {
                    Status.LOADING -> {
                        progressbarLoading.show()
                    }

                    Status.SUCCESS -> {
                        progressbarLoading.hide()

                        showWeatherInfo(dataWrapper.data)
                    }

                    Status.ERROR -> {
                        Timber.tag(TAG).e(dataWrapper.error)
                        progressbarLoading.hide()

                        context?.showToast(
                            resources.getString(R.string.home_load_data_failed)
                        )
                    }
                }

            }
    }

    //**********************************************************************************************
    private fun showWeatherInfo(oneCallModel: OneCallModel?) {
        if (oneCallModel == null) {
            return
        }
        mOneCallModel = oneCallModel

        setCurrentTemp()

        if (mSelectedDayInfo == null) {
            setDailyListData(oneCallModel)
        }

        setHourlyListData(oneCallModel)

        if (!oneCallModel.current?.weather.isNullOrEmpty()) {

            when (oneCallModel.current!!.weather!![0].id / 100) {
                2L -> imageWeather.setImageResource(R.drawable.ic_thunderstorm)

                3L -> imageWeather.setImageResource(R.drawable.ic_mist)

                5L -> imageWeather.setImageResource(R.drawable.ic_rain)

                6L -> imageWeather.setImageResource(R.drawable.ic_snow)

                7L -> imageWeather.setImageResource(R.drawable.ic_mist)

                8L -> if (oneCallModel.current!!.weather!![0].id.rem(100) == 0L) {
                    when {
                        mCurrentHour < 7 -> {
                            imageWeather.setImageResource(R.drawable.ic_clear_sky_dawn)
                        }
                        mCurrentHour < 12 -> {
                            imageWeather.setImageResource(R.drawable.ic_clear_sky_morning)
                        }
                        mCurrentHour < 17 -> {
                            imageWeather.setImageResource(R.drawable.ic_clear_sky_morning)
                        }
                        mCurrentHour < 20 -> {
                            imageWeather.setImageResource(R.drawable.ic_clear_sky_evening)
                        }
                        else -> {
                            imageWeather.setImageResource(R.drawable.ic_clear_sky_night)
                        }
                    }
                }
                else {
                    when {
                        mCurrentHour < 7 -> {
                            imageWeather.setImageResource(R.drawable.ic_few_cloud_dawn)
                        }
                        mCurrentHour < 12 -> {
                            imageWeather.setImageResource(R.drawable.ic_few_cloud_morning)
                        }
                        mCurrentHour < 17 -> {
                            imageWeather.setImageResource(R.drawable.ic_few_cloud_morning)
                        }
                        mCurrentHour < 20 -> {
                            imageWeather.setImageResource(R.drawable.ic_few_cloud_evening)
                        }
                        else -> {
                            imageWeather.setImageResource(R.drawable.ic_few_clound_night)
                        }
                    }
                }

            }

        }
    }

    private fun setCurrentTemp() {

        if (mSelectedDayInfo == null) {
            textCurrentTemp.text = String.format(
                resources.getString(R.string.home_temp_format),
                mOneCallModel?.current?.temp?.toInt() ?: 0
            )

            if (!mOneCallModel!!.daily.isNullOrEmpty()) {
                textMinMaxTemp.text = String.format(
                    resources.getString(R.string.home_temp_minmax_format),
                    mOneCallModel!!.daily!![0].temp?.max?.toInt() ?: 0,
                    mOneCallModel!!.daily!![0].temp?.min?.toInt() ?: 0
                )
            }

        }
        else {

            val temp = when {
                mCurrentHour < 7 -> mSelectedDayInfo!!.temp?.morn ?: 0F
                mCurrentHour < 12 -> mSelectedDayInfo!!.temp?.morn ?: 0F
                mCurrentHour < 17 -> mSelectedDayInfo!!.temp?.eve ?: 0F
                mCurrentHour < 20 -> mSelectedDayInfo!!.temp?.eve ?: 0F
                else -> mSelectedDayInfo!!.temp?.night ?: 0F
            }

            textCurrentTemp.text = String.format(
                resources.getString(R.string.home_temp_format),
                temp.toInt()
            )

            textMinMaxTemp.text = String.format(
                resources.getString(R.string.home_temp_minmax_format),
                mSelectedDayInfo!!.temp?.max?.toInt() ?: 0,
                mSelectedDayInfo!!.temp?.min?.toInt() ?: 0
            )

        }

    }

    private fun setHourlyListData(oneCallModel: OneCallModel) {
        if (!oneCallModel.hourly.isNullOrEmpty()) {
            val dailyList = ArrayList<InfoModel>()
            val calendar = Calendar.getInstance()
            val currentDay = calendar.get(Calendar.DAY_OF_MONTH)

            for (hourlyInfo in oneCallModel.hourly!!) {
                calendar.timeInMillis = hourlyInfo.dt * 1000

                if (calendar.get(Calendar.DAY_OF_MONTH) == currentDay) {
                    dailyList.add(hourlyInfo)
                }
            }

            mHourlyInfoAdapter.setDataList(dailyList)
        }
    }

    private fun setDailyListData(oneCallModel: OneCallModel) {

        if (!oneCallModel.daily.isNullOrEmpty()) {

            val dailyList = ArrayList<DailyInfoModel>(oneCallModel.daily!!)
            val calendar = Calendar.getInstance()
            val currentDay = calendar.get(Calendar.DAY_OF_MONTH)

            calendar.timeInMillis = oneCallModel.daily!![0].dt * 1000
            val firstDay = calendar.get(Calendar.DAY_OF_MONTH)

            if (firstDay == currentDay) {
                dailyList.removeAt(0)
            }

            mDailyInfoAdapter.setDataList(dailyList)
        }

    }

    //**********************************************************************************************
    private fun changeBackgroundColor() {
        val calendar = Calendar.getInstance()
        mCurrentHour = calendar.get(Calendar.HOUR_OF_DAY)

        context?.let {

            val gradientDrawable = when {
                mCurrentHour < 7 -> {
                    mStatusBarColor = ContextCompat.getColor(it, R.color.dawnTopGradientColor)

                    GradientDrawable(
                        GradientDrawable.Orientation.TOP_BOTTOM,
                        intArrayOf(
                            ContextCompat.getColor(it, R.color.dawnTopGradientColor),
                            ContextCompat.getColor(it, R.color.dawnBottomGradientColor)
                        )
                    )
                }
                mCurrentHour < 12 -> {
                    mStatusBarColor = ContextCompat.getColor(it, R.color.morningTopGradientColor)

                    GradientDrawable(
                        GradientDrawable.Orientation.TOP_BOTTOM,
                        intArrayOf(
                            ContextCompat.getColor(it, R.color.morningTopGradientColor),
                            ContextCompat.getColor(it, R.color.morningBottomGradientColor)
                        )
                    )
                }
                mCurrentHour < 17 -> {
                    mStatusBarColor = ContextCompat.getColor(it, R.color.noonTopGradientColor)

                    GradientDrawable(
                        GradientDrawable.Orientation.TOP_BOTTOM,
                        intArrayOf(
                            ContextCompat.getColor(it, R.color.noonTopGradientColor),
                            ContextCompat.getColor(it, R.color.noonBottomGradientColor)
                        )
                    )
                }
                mCurrentHour < 20 -> {
                    mStatusBarColor = ContextCompat.getColor(it, R.color.eveningTopGradientColor)

                    GradientDrawable(
                        GradientDrawable.Orientation.TOP_BOTTOM,
                        intArrayOf(
                            ContextCompat.getColor(it, R.color.eveningTopGradientColor),
                            ContextCompat.getColor(it, R.color.eveningBottomGradientColor)
                        )
                    )
                }
                else -> {
                    mStatusBarColor = ContextCompat.getColor(it, R.color.nightTopGradientColor)

                    GradientDrawable(
                        GradientDrawable.Orientation.TOP_BOTTOM,
                        intArrayOf(
                            ContextCompat.getColor(it, R.color.nightTopGradientColor),
                            ContextCompat.getColor(it, R.color.nightBottomGradientColor)
                        )
                    )
                }
            }

            layoutRoot.background = gradientDrawable

            changeStatusBarColor()

        }

    }

    //**********************************************************************************************
    private fun changeStatusBarColor() {
        activity?.let {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                val window = it.window
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
                window.statusBarColor = mStatusBarColor
            }
        }
    }

}