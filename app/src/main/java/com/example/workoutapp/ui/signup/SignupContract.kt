package com.example.workoutapp.ui.signup

import com.example.workoutapp.ui.common.BasePresenter
import com.example.workoutapp.ui.common.BaseView

interface SignupContract {

    interface View : BaseView<Presenter> {
        fun openRegisterActivity()
        fun openLoginActivity()
    }

    interface Presenter : BasePresenter<View> {
        fun registerClicked()
        fun loginClicked()
    }

}