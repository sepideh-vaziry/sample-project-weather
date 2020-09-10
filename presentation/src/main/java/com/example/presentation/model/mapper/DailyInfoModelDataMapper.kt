package com.example.presentation.model.mapper

import com.example.domain.entity.DailyInfo
import com.example.presentation.model.DailyInfoModel
import javax.inject.Inject

class DailyInfoModelDataMapper @Inject constructor(
    private val weatherModelDataMapper: WeatherModelDataMapper,
    private val tempModelDataMapper: TempModelDataMapper
) {

    /**
     * Transform list of [DailyInfo] into list of [DailyInfoModel]
     *
     * @param dailyInfoList List of object will be transformed.
     * @return List of [DailyInfoModel] if valid [DailyInfo] otherwise null.
     */
    fun transform(dailyInfoList: List<DailyInfo>?) : List<DailyInfoModel> {
        val dailyInfoModelList: MutableList<DailyInfoModel> = ArrayList()

        if (dailyInfoList == null) {
            return dailyInfoModelList
        }

        for (dailyInfo in dailyInfoList) {
            val dailyInfoModel = transform(dailyInfo)

            if (dailyInfoModel != null) {
                dailyInfoModelList.add(dailyInfoModel)
            }
        }

        return dailyInfoModelList
    }

    /**
     * Transform a [dailyInfo] into an [DailyInfoModel]
     *
     * @param dailyInfo Object to be transformed.
     * @return [DailyInfoModel] if valid [dailyInfo] otherwise null.
     */
    fun transform(dailyInfo: DailyInfo?): DailyInfoModel? {
        if (dailyInfo == null) {
            return null
        }

        val dailyInfoModel = DailyInfoModel()

        dailyInfoModel.dt = dailyInfo.dt
        dailyInfoModel.temp = tempModelDataMapper.transform(dailyInfo.temp)
        dailyInfoModel.pressure = dailyInfo.pressure
        dailyInfoModel.humidity = dailyInfo.humidity
        dailyInfoModel.weather = weatherModelDataMapper.transform(dailyInfo.weather)

        return dailyInfoModel
    }
}