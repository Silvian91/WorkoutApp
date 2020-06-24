package com.example.workoutapp.ui.home.adapter

import com.example.workoutapp.domain.inspirationalquote.model.QuoteModel
import com.example.workoutapp.domain.openweathermap.model.WeatherModel

sealed class HomeItemWrapper(
    val type: ItemType
) {

    enum class ItemType {
        WEATHER,
        QUOTE,
        ACTIONS
    }

    data class Weather(val weather: WeatherModel) : HomeItemWrapper(ItemType.WEATHER)

    data class Quote(val quoteText: QuoteModel) : HomeItemWrapper(ItemType.QUOTE)

    object Actions : HomeItemWrapper(ItemType.ACTIONS)

}