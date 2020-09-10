package com.example.data.entity.mapper

import com.example.data.entity.TempEntity
import com.example.domain.entity.Temp
import javax.inject.Inject

class TempEntityDataMapper @Inject constructor() {

    /**
     * Transform list of [TempEntity] into list of [Temp]
     *
     * @param tempEntityList List of object will be transformed.
     * @return List of [Temp] if valid [TempEntity] otherwise null.
     */
    fun transform(tempEntityList: List<TempEntity>?) : List<Temp> {
        val tempList: MutableList<Temp> = ArrayList()

        if (tempEntityList == null) {
            return tempList
        }

        for (tempEntity in tempEntityList) {
            val temp = transform(tempEntity)

            if (temp != null) {
                tempList.add(temp)
            }
        }

        return tempList
    }

    /**
     * Transform a [tempEntity] into an [Temp]
     *
     * @param tempEntity Object to be transformed.
     * @return [Temp] if valid [tempEntity] otherwise null.
     */
    fun transform(tempEntity: TempEntity?): Temp? {
        if (tempEntity == null) {
            return null
        }

        val temp = Temp()

        temp.day = tempEntity.day
        temp.min = tempEntity.min
        temp.max = tempEntity.max
        temp.night = tempEntity.night
        temp.eve = tempEntity.eve
        temp.morn = tempEntity.morn

        return temp
    }

}