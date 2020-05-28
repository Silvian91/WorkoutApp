package com.example.workoutapp.ui.profile

import com.example.workoutapp.ui.common.BasePresenter
import com.example.workoutapp.ui.common.BaseView

interface ProfileContract {

    interface View : BaseView<Presenter> {
        fun showUsername(username: String)
        fun showLogin()
        fun showError()
        fun showLoginActivity()
        fun showLogOutConfirmationDialog()
    }

    interface Presenter : BasePresenter<View> {
        fun onLogOutConfirmed()
        fun logOutClicked()
    }
}