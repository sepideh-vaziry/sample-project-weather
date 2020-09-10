package com.example.presentation.model.mapper

import com.example.domain.entity.Info
import com.example.presentation.model.InfoModel
import javax.inject.Inject

class InfoModelDataMapper @Inject constructor(
    private val weatherModelDataMapper: WeatherModelDataMapper
) {

    /**
     * Transform list of [Info] into list of [InfoModel]
     *
     * @param infoList List of object will be transformed.
     * @return List of [InfoModel] if valid [Info] otherwise null.
     */
    fun transform(infoList: List<Info>?) : List<InfoModel> {
        val infoModelList: MutableList<InfoModel> = ArrayList()

        if (infoList == null) {
            return infoModelList
        }

        for (info in infoList) {
            val infoModel = transform(info)

            if (infoModel != null) {
                infoModelList.add(infoModel)
            }
        }

        return infoModelList
    }

    /**
     * Transform a [info] into an [InfoModel]
     *
     * @param info Object to be transformed.
     * @return [InfoModel] if valid [info] otherwise null.
     */
    fun transform(info: Info?): InfoModel? {
        if (info == null) {
            return null
        }

        val infoModel = InfoModel()

        infoModel.dt = info.dt
        infoModel.temp = info.temp
        infoModel.pressure = info.pressure
        infoModel.humidity = info.humidity
        infoModel.weather = weatherModelDataMapper.transform(info.weather)

        return infoModel
    }
}