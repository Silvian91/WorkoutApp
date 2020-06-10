package com.example.workoutapp.ui.main

import com.example.workoutapp.domain.inspirationalquote.model.InspirationalQuoteModel
import com.example.workoutapp.domain.openweathermap.model.WeatherModel
import com.example.workoutapp.ui.common.BasePresenter
import com.example.workoutapp.ui.common.BaseView

interface HomeContract {

    interface View : BaseView<Presenter> {
        fun displayCurrentWeather(name: String, temp: String)
        fun displayQuote(quote: InspirationalQuoteModel)
        fun showNetworkError()
        fun openAddWorkoutActivity()
        fun openShowWorkoutActivity()
    }

    interface Presenter : BasePresenter<View> {
        fun addWorkoutClicked()
        fun showWorkoutClicked()
    }
}