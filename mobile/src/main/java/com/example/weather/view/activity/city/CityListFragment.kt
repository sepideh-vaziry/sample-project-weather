package com.example.weather.view.activity.city

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
import com.example.presentation.viewmodel.base.Status
import com.example.presentation.viewmodel.city.CityListViewModel
import com.example.presentation.viewmodel.city.CityViewModel
import com.example.weather.R
import com.example.weather.base.hide
import com.example.weather.base.linearLayout
import com.example.weather.base.show
import com.example.weather.base.showToast
import com.example.weather.databinding.FragmentCityListBinding
import com.example.weather.view.activity.base.BaseFragment
import com.example.weather.view.adapter.CityAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_city_list.*
import timber.log.Timber
import java.util.*
import javax.inject.Inject


@AndroidEntryPoint
class CityListFragment : BaseFragment() {

    companion object {
        private val TAG = CityListFragment::class.java.simpleName
    }

    @Inject
    lateinit var cityAdapter: CityAdapter

    private val mCityListViewModel: CityListViewModel by viewModels()
    private val mCityViewModel: CityViewModel by viewModels()

    private lateinit var mNavController: NavController

    private var mStatusBarColor: Int = Color.WHITE

    //**********************************************************************************************
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentCityListBinding.inflate(
            inflater, container, false
        )

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        changeBackgroundColor()

        mNavController = view.findNavController()

        listConfig()

        loadCityList()

    }

    //**********************************************************************************************
    private fun listConfig() {
        //Create adapter
        cityAdapter.setOnItemClickListener { _, cityModel ->
            mCityViewModel.setSelectedCity(cityModel)
            mNavController.popBackStack()
        }

        //Recycler config
        recyclerViewCity.apply {
            linearLayout(context)
            adapter = cityAdapter
        }
    }

    //**********************************************************************************************
    private fun loadCityList() {

        mCityListViewModel.getCityList()
            .observe(viewLifecycleOwner) { dataWrapper ->

                when (dataWrapper.status) {
                    Status.LOADING -> {
                        progressbarLoading.show()
                    }

                    Status.SUCCESS -> {
                        progressbarLoading.hide()

                        dataWrapper.data?.let {
                            cityAdapter.setDataList(it)
                        }
                    }

                    Status.ERROR -> {
                        Timber.tag(TAG).e(dataWrapper.error)
                        progressbarLoading.hide()

                        context?.showToast(
                            resources.getString(R.string.city_list_load_failed_message)
                        )

                    }
                }

            }

    }

    //**********************************************************************************************
    private fun changeBackgroundColor() {
        val calendar = Calendar.getInstance()
        val hour = calendar.get(Calendar.HOUR_OF_DAY)

        context?.let {

            val gradientDrawable = when {
                hour < 7 -> {
                    mStatusBarColor = ContextCompat.getColor(it, R.color.dawnTopGradientColor)

                    GradientDrawable(
                        GradientDrawable.Orientation.TOP_BOTTOM,
                        intArrayOf(
                            ContextCompat.getColor(it, R.color.dawnTopGradientColor),
                            ContextCompat.getColor(it, R.color.dawnBottomGradientColor)
                        )
                    )
                }
                hour < 12 -> {
                    mStatusBarColor = ContextCompat.getColor(it, R.color.morningTopGradientColor)

                    GradientDrawable(
                        GradientDrawable.Orientation.TOP_BOTTOM,
                        intArrayOf(
                            ContextCompat.getColor(it, R.color.morningTopGradientColor),
                            ContextCompat.getColor(it, R.color.morningBottomGradientColor)
                        )
                    )
                }
                hour < 17 -> {
                    mStatusBarColor = ContextCompat.getColor(it, R.color.noonTopGradientColor)

                    GradientDrawable(
                        GradientDrawable.Orientation.TOP_BOTTOM,
                        intArrayOf(
                            ContextCompat.getColor(it, R.color.noonTopGradientColor),
                            ContextCompat.getColor(it, R.color.noonBottomGradientColor)
                        )
                    )
                }
                hour < 20 -> {
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