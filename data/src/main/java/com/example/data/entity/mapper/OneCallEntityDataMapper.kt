package com.example.data.entity.mapper

import com.example.data.entity.OneCallEntity
import com.example.domain.entity.OneCall
import javax.inject.Inject

class OneCallEntityDataMapper @Inject constructor(
    private val infoEntityDataMapper: InfoEntityDataMapper,
    private val dailyInfoEntityDataMapper: DailyInfoEntityDataMapper
) {

    /**
     * Transform list of [OneCallEntity] into list of [OneCall]
     *
     * @param oneCallEntityList List of object will be transformed.
     * @return List of [OneCall] if valid [OneCallEntity] otherwise null.
     */
    fun transform(oneCallEntityList: List<OneCallEntity>?) : List<OneCall> {
        val oneCallList: MutableList<OneCall> = ArrayList()

        if (oneCallEntityList == null) {
            return oneCallList
        }

        for (oneCallEntity in oneCallEntityList) {
            val oneCall = transform(oneCallEntity)

            if (oneCall != null) {
                oneCallList.add(oneCall)
            }
        }

        return oneCallList
    }

    /**
     * Transform a [oneCallEntity] into an [OneCall]
     *
     * @param oneCallEntity Object to be transformed.
     * @return [OneCall] if valid [oneCallEntity] otherwise null.
     */
    fun transform(oneCallEntity: OneCallEntity?): OneCall? {
        if (oneCallEntity == null) {
            return null
        }

        val oneCall = OneCall()

        oneCall.current = infoEntityDataMapper.transform(oneCallEntity.current)
        oneCall.hourly = infoEntityDataMapper.transform(oneCallEntity.hourly)
        oneCall.daily = dailyInfoEntityDataMapper.transform(oneCallEntity.daily)

        return oneCall
    }

}