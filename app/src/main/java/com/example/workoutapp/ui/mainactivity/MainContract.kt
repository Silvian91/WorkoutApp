package com.example.workoutapp.ui.mainactivity

import com.example.workoutapp.ui.common.BasePresenter
import com.example.workoutapp.ui.common.BaseView

interface MainContract {

    interface View : BaseView<Presenter> {

    }

    interface Presenter : BasePresenter<View> {

        fun getChuckNorrisQuote()

    }
}