package com.example.workoutapp.ui.main.recyclerviewadapter.viewholder

import android.view.View
import com.example.workoutapp.R.string.text_home_weather
import com.example.workoutapp.ui.common.BaseViewHolder
import com.example.workoutapp.ui.main.recyclerviewadapter.HomeItemsWrapper.Weather
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.view_holder_home_weather.*

class WeatherViewHolder(
    override val containerView: View
) : BaseViewHolder<Weather>(containerView), LayoutContainer {

    override fun bind(model: Weather) {
        weather_api.text = itemView.context.getString(
            text_home_weather,
            model.weather.name,
            model.weather.temp.toInt().toString()
        )
        progress_circular_weather.visibility = View.GONE
    }

}