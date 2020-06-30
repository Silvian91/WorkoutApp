package com.example.workoutapp.ui.splash

import com.example.workoutapp.ui.common.BasePresenter
import com.example.workoutapp.ui.common.BaseView

interface SplashContract {

    interface View: BaseView<Presenter>{
        fun openLogin()
        fun openRegister()
    }

    interface Presenter: BasePresenter<View>

}