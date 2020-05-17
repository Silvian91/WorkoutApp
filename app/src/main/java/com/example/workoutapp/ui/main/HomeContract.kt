package com.example.workoutapp.ui.main

import com.example.workoutapp.domain.inspirationalquote.model.InspirationalQuoteModel
import com.example.workoutapp.domain.openweathermap.model.WeatherModel
import com.example.workoutapp.ui.common.BasePresenter
import com.example.workoutapp.ui.common.BaseView

interface HomeContract {

    interface View : BaseView<Presenter> {
        fun displayCurrentWeather(weather: WeatherModel)
        fun displayQuote(quote: InspirationalQuoteModel)
    }

    interface Presenter : BasePresenter<View>
}