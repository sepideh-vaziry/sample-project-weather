package com.example.weather.view.item

import android.content.Context
import android.view.View
import com.example.presentation.model.CityModel
import com.example.weather.view.item.base.BaseItem
import kotlinx.android.synthetic.main.item_city.view.*

class CityItem(itemView: View) : BaseItem<CityModel>(itemView) {

    override fun bind(context: Context, contentObject: CityModel?) {
        if (contentObject == null) {
            return
        }

        itemView.textCityName.text = contentObject.name
    }

    override fun loading(isVisible: Boolean) {
        //Nothing
    }

}