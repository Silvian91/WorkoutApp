package com.example.workoutapp.ui.profile

import com.example.workoutapp.ui.common.BasePresenter
import com.example.workoutapp.ui.common.BaseView

interface ProfileContract {

    interface View : BaseView<Presenter> {
        fun showUsername(username: String)
        fun showLogin()
        fun showError()
    }

    interface Presenter : BasePresenter<View>
}