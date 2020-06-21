package com.example.workoutapp.ui.main.recyclerviewadapter

import com.example.workoutapp.domain.inspirationalquote.model.QuoteModel
import com.example.workoutapp.domain.openweathermap.model.WeatherModel

sealed class HomeItemsWrapper(
    val type: ItemType
) {

    enum class ItemType {
        WEATHER,
        QUOTE,
        ACTIONS
    }

    data class Weather(val weather: WeatherModel) : HomeItemsWrapper(ItemType.WEATHER)

    data class Quote(val quoteText: QuoteModel) : HomeItemsWrapper(ItemType.QUOTE)

    object Actions : HomeItemsWrapper(ItemType.ACTIONS)

}