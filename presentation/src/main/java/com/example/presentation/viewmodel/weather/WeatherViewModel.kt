package com.example.presentation.viewmodel.weather

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.interactor.UseCaseResult
import com.example.domain.interactor.weather.GetAllWeatherInfoUseCase
import com.example.presentation.model.CityModel
import com.example.presentation.model.OneCallModel
import com.example.presentation.model.mapper.OneCallModelDataMapper
import com.example.presentation.viewmodel.base.DataWrapper

class WeatherViewModel @ViewModelInject constructor(
    private val getAllWeatherInfoUseCase: GetAllWeatherInfoUseCase,
    private val oneCallModelDataMapper: OneCallModelDataMapper
) : ViewModel() {

    //**********************************************************************************************
    fun getAllWeatherInfo(selectedCity: CityModel) : LiveData<DataWrapper<OneCallModel>> {
        val liveData = MutableLiveData<DataWrapper<OneCallModel>>()
        liveData.value = DataWrapper.loading()

        val params = GetAllWeatherInfoUseCase.Params(
            selectedCity.coordinate?.latitude ?: 0.0,
            selectedCity.coordinate?.longitude ?: 0.0
        )

        getAllWeatherInfoUseCase.execute(viewModelScope, params) { useCaseResult ->

            when (useCaseResult) {

                is UseCaseResult.Success -> {
                    liveData.value = DataWrapper.success(
                        oneCallModelDataMapper.transform(useCaseResult.data)
                    )
                }

                is UseCaseResult.Error -> {
                    liveData.value = DataWrapper.error(useCaseResult.throwable)
                }

            } //End of when

        } //End of execute

        return liveData
    }

}