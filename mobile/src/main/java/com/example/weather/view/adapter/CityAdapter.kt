package com.example.weather.view.adapter

import android.content.Context
import android.view.View
import com.example.presentation.model.CityModel
import com.example.weather.R
import com.example.weather.view.adapter.base.BaseRecyclerAdapter
import com.example.weather.view.item.base.BaseItem
import com.example.weather.view.item.CityItem
import javax.inject.Inject

class CityAdapter @Inject constructor(
    context: Context
) : BaseRecyclerAdapter<CityModel>(context) {

    override fun getItemResId(viewType: Int): Int {
        return R.layout.item_city
    }

    override fun getItemViewHolder(view: View): BaseItem<CityModel> {
        return CityItem(view)
    }
}