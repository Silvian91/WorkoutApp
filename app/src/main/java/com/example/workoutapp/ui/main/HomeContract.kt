package com.example.workoutapp.ui.main

import com.example.workoutapp.ui.common.BasePresenter
import com.example.workoutapp.ui.common.BaseView

interface HomeContract {

    interface View : BaseView<Presenter> {
        fun displayWeather(name: String, temp: String)
        fun displayQuote(quote: String, author: String)
        fun showNetworkError()
        fun openAddWorkoutActivity()
        fun openShowWorkoutActivity()
    }

    interface Presenter : BasePresenter<View> {
        fun addWorkoutClicked()
        fun showWorkoutClicked()
    }
}