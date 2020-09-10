package com.example.presentation.model.mapper

import com.example.domain.entity.Temp
import com.example.presentation.model.TempModel
import javax.inject.Inject

class TempModelDataMapper @Inject constructor() {

    /**
     * Transform list of [Temp] into list of [TempModel]
     *
     * @param tempList List of object will be transformed.
     * @return List of [TempModel] if valid [Temp] otherwise null.
     */
    fun transform(tempList: List<Temp>?) : List<TempModel> {
        val tempModelList: MutableList<TempModel> = ArrayList()

        if (tempList == null) {
            return tempModelList
        }

        for (temp in tempList) {
            val tempModel = transform(temp)

            if (tempModel != null) {
                tempModelList.add(tempModel)
            }
        }

        return tempModelList
    }

    /**
     * Transform a [temp] into an [TempModel]
     *
     * @param temp Object to be transformed.
     * @return [TempModel] if valid [temp] otherwise null.
     */
    fun transform(temp: Temp?): TempModel? {
        if (temp == null) {
            return null
        }

        val tempModel = TempModel()

        tempModel.day = temp.day
        tempModel.min = temp.min
        tempModel.max = temp.max
        tempModel.night = temp.night
        tempModel.eve = temp.eve
        tempModel.morn = temp.morn

        return tempModel
    }
}