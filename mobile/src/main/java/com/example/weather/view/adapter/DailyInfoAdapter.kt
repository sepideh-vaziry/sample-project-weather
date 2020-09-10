package com.example.weather.view.adapter

import android.content.Context
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.presentation.model.DailyInfoModel
import com.example.weather.R
import com.example.weather.view.adapter.base.BaseRecyclerAdapter
import com.example.weather.view.item.DailyInfoItem
import com.example.weather.view.item.base.BaseItem
import javax.inject.Inject

class DailyInfoAdapter @Inject constructor(
    private val context: Context
) : BaseRecyclerAdapter<DailyInfoModel>(context) {

    private var mSelectedPosition: Int = -1

    //**********************************************************************************************
    override fun getItemResId(viewType: Int): Int {
        return R.layout.item_daily_info
    }

    override fun getItemViewHolder(view: View): BaseItem<DailyInfoModel> {
        return DailyInfoItem(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)

        if (position == mSelectedPosition) {
            holder.itemView.setBackgroundColor(
                ContextCompat.getColor(context, R.color.daily_info_item_selected_background)
            )
        }
        else {
            holder.itemView.background = null
        }

    }

    //**********************************************************************************************
    internal fun setSelectedPosition(position: Int) {
        mSelectedPosition = position

        notifyDataSetChanged()
    }

}