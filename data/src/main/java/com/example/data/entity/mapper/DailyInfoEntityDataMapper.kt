package com.example.data.entity.mapper

import com.example.data.entity.DailyInfoEntity
import com.example.domain.entity.DailyInfo
import javax.inject.Inject

class DailyInfoEntityDataMapper @Inject constructor(
    private val weatherEntityDataMapper: WeatherEntityDataMapper,
    private val tempEntityDataMapper: TempEntityDataMapper
) {

    /**
     * Transform list of [DailyInfoEntity] into list of [DailyInfo]
     *
     * @param dailyDailyInfoEntityList List of object will be transformed.
     * @return List of [DailyInfo] if valid [DailyInfoEntity] otherwise null.
     */
    fun transform(dailyDailyInfoEntityList: List<DailyInfoEntity>?) : List<DailyInfo> {
        val dailyDailyInfoList: MutableList<DailyInfo> = ArrayList()

        if (dailyDailyInfoEntityList == null) {
            return dailyDailyInfoList
        }

        for (dailyDailyInfoEntity in dailyDailyInfoEntityList) {
            val dailyDailyInfo = transform(dailyDailyInfoEntity)

            if (dailyDailyInfo != null) {
                dailyDailyInfoList.add(dailyDailyInfo)
            }
        }

        return dailyDailyInfoList
    }

    /**
     * Transform a [dailyDailyInfoEntity] into an [DailyInfo]
     *
     * @param dailyDailyInfoEntity Object to be transformed.
     * @return [DailyInfo] if valid [dailyDailyInfoEntity] otherwise null.
     */
    fun transform(dailyDailyInfoEntity: DailyInfoEntity?): DailyInfo? {
        if (dailyDailyInfoEntity == null) {
            return null
        }

        val dailyDailyInfo = DailyInfo()

        dailyDailyInfo.dt = dailyDailyInfoEntity.dt
        dailyDailyInfo.temp = tempEntityDataMapper.transform(dailyDailyInfoEntity.temp)
        dailyDailyInfo.pressure = dailyDailyInfoEntity.pressure
        dailyDailyInfo.humidity = dailyDailyInfoEntity.humidity
        dailyDailyInfo.weather = weatherEntityDataMapper.transform(dailyDailyInfoEntity.weather)

        return dailyDailyInfo
    }

}