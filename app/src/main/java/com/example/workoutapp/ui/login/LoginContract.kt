package com.example.workoutapp.ui.login

import com.example.workoutapp.ui.common.BasePresenter
import com.example.workoutapp.ui.common.BaseView

interface LoginContract {

    interface View : BaseView<Presenter> {

    }

    interface Presenter : BasePresenter<View> {

    }
}