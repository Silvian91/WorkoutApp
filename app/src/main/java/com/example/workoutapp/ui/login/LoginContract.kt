package com.example.workoutapp.ui.login

import com.example.workoutapp.ui.common.BasePresenter
import com.example.workoutapp.ui.common.BaseView

interface LoginContract {

    interface View : BaseView<Presenter> {
        fun showMain()
        fun showErrorInvalidCredentials()
        fun showErrorUserDoesNotExist()
        fun showUnknownError()
    }

    interface Presenter : BasePresenter<View> {
        fun onLoginClicked(username: String, password: String)
    }
}