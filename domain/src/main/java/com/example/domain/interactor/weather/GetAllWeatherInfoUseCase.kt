package com.example.domain.interactor.weather

import com.example.domain.entity.OneCall
import com.example.domain.interactor.UseCase
import com.example.domain.repository.WeatherRepository
import javax.inject.Inject

class GetAllWeatherInfoUseCase @Inject constructor(
    private val weatherRepository: WeatherRepository
) : UseCase<OneCall?, GetAllWeatherInfoUseCase.Params>() {

    class Params(val latitude: Double, val longitude: Double)

    //**********************************************************************************************
    override suspend fun executeOnBackground(params: Params?): OneCall? {
        return weatherRepository.getAllWeatherInfo(params!!.latitude, params.longitude)
    }

}