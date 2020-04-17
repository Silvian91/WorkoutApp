package com.example.workoutapp.ui.register

import com.example.workoutapp.ui.common.BasePresenter
import com.example.workoutapp.ui.common.BaseView

interface RegisterContract {

    interface View : BaseView<Presenter> {
        fun showMain()
        fun showError()
    }

    interface Presenter : BasePresenter<View> {
        fun onContinueClicked(
            username: String,
            password: String
        )
    }
}