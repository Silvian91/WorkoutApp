package com.example.workoutapp.ui.home.adapter.viewholder

import android.view.View
import com.example.workoutapp.R.string.text_home_weather
import com.example.workoutapp.ui.common.BaseViewHolder
import com.example.workoutapp.ui.home.adapter.HomeItemWrapper
import com.example.workoutapp.ui.home.adapter.HomeItemWrapper.Weather
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.view_holder_home_weather.*

class WeatherViewHolder(
    override val containerView: View
) : BaseViewHolder<HomeItemWrapper>(containerView), LayoutContainer {

    override fun bind(model: HomeItemWrapper) {
        model as Weather
        weather_api.text = itemView.context.getString(
            text_home_weather,
            model.weather.name,
            model.weather.temp.toInt().toString()
        )
        progress_circular_weather.visibility = View.GONE
    }

}