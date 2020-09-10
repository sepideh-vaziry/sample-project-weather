package com.example.presentation.model.mapper

import com.example.domain.entity.OneCall
import com.example.presentation.model.OneCallModel
import javax.inject.Inject

class OneCallModelDataMapper @Inject constructor(
    private val infoModelDataMapper: InfoModelDataMapper,
    private val dailyInfoModelDataMapper: DailyInfoModelDataMapper
) {

    /**
     * Transform list of [OneCall] into list of [OneCallModel]
     *
     * @param oneCallList List of object will be transformed.
     * @return List of [OneCallModel] if valid [OneCall] otherwise null.
     */
    fun transform(oneCallList: List<OneCall>?) : List<OneCallModel> {
        val oneCallModelList: MutableList<OneCallModel> = ArrayList()

        if (oneCallList == null) {
            return oneCallModelList
        }

        for (oneCall in oneCallList) {
            val oneCallModel = transform(oneCall)

            if (oneCallModel != null) {
                oneCallModelList.add(oneCallModel)
            }
        }

        return oneCallModelList
    }

    /**
     * Transform a [oneCall] into an [OneCallModel]
     *
     * @param oneCall Object to be transformed.
     * @return [OneCallModel] if valid [oneCall] otherwise null.
     */
    fun transform(oneCall: OneCall?): OneCallModel? {
        if (oneCall == null) {
            return null
        }

        val oneCallModel = OneCallModel()

        oneCallModel.current = infoModelDataMapper.transform(oneCall.current)
        oneCallModel.hourly = infoModelDataMapper.transform(oneCall.hourly)
        oneCallModel.daily = dailyInfoModelDataMapper.transform(oneCall.daily)

        return oneCallModel
    }
    
}