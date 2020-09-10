package com.example.data.entity.mapper

import com.example.data.entity.InfoEntity
import com.example.domain.entity.Info
import javax.inject.Inject

class InfoEntityDataMapper @Inject constructor(
    private val weatherEntityDataMapper: WeatherEntityDataMapper
) {

    /**
     * Transform list of [InfoEntity] into list of [Info]
     *
     * @param infoEntityList List of object will be transformed.
     * @return List of [Info] if valid [InfoEntity] otherwise null.
     */
    fun transform(infoEntityList: List<InfoEntity>?) : List<Info> {
        val infoList: MutableList<Info> = ArrayList()

        if (infoEntityList == null) {
            return infoList
        }

        for (infoEntity in infoEntityList) {
            val info = transform(infoEntity)

            if (info != null) {
                infoList.add(info)
            }
        }

        return infoList
    }

    /**
     * Transform a [infoEntity] into an [Info]
     *
     * @param infoEntity Object to be transformed.
     * @return [Info] if valid [infoEntity] otherwise null.
     */
    fun transform(infoEntity: InfoEntity?): Info? {
        if (infoEntity == null) {
            return null
        }

        val info = Info()

        info.dt = infoEntity.dt
        info.temp = infoEntity.temp
        info.pressure = infoEntity.pressure
        info.humidity = infoEntity.humidity
        info.weather = weatherEntityDataMapper.transform(infoEntity.weather)

        return info
    }
    
}