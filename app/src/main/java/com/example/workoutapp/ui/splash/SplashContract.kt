package com.example.workoutapp.ui.splash

import com.example.workoutapp.ui.common.BasePresenter
import com.example.workoutapp.ui.common.BaseView

interface SplashContract {

    interface View: BaseView<Presenter>{
        fun openLoginActivity()
        fun openRegisterActivity()
        fun showError()
    }

    interface Presenter: BasePresenter<View>

}