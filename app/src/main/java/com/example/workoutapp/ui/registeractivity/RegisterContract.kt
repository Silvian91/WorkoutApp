package com.example.workoutapp.ui.registeractivity

import com.example.workoutapp.ui.common.BasePresenter
import com.example.workoutapp.ui.common.BaseView

interface RegisterContract {

    interface View : BaseView<Presenter> {

    }

    interface Presenter : BasePresenter<View>
}