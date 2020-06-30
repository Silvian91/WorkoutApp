package com.example.workoutapp.ui.login

import com.example.workoutapp.ui.common.BasePresenter
import com.example.workoutapp.ui.common.BaseView

interface LoginContract {

    interface View : BaseView<Presenter> {
        fun showHome()
        fun showError(errorType: ErrorType)
        fun openRegisterActivity()
    }

    interface Presenter : BasePresenter<View> {
        fun onLoginClicked(username: String, password: String)
        fun onSignUpClicked()
    }

    enum class ErrorType(val error: String) {
        INVALID_CREDENTIALS("Invalid username or password"),
        USER_DOES_NOT_EXIST("This user does not exist"),
        UNKNOWN("And unknown error has occurred")
    }
}