package com.example.weather.view.adapter

import android.content.Context
import android.view.View
import com.example.presentation.model.InfoModel
import com.example.weather.R
import com.example.weather.view.adapter.base.BaseRecyclerAdapter
import com.example.weather.view.item.HourlyInfoItem
import com.example.weather.view.item.base.BaseItem
import javax.inject.Inject

class HourlyInfoAdapter @Inject constructor(
    context: Context
) : BaseRecyclerAdapter<InfoModel>(context) {

    override fun getItemResId(viewType: Int): Int {
        return R.layout.item_hourly_info
    }

    override fun getItemViewHolder(view: View): BaseItem<InfoModel> {
        return HourlyInfoItem(view)
    }
}