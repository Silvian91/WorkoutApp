package com.example.workoutapp.ui.start

import com.example.workoutapp.ui.common.BasePresenter
import com.example.workoutapp.ui.common.BaseView

interface StartContract {

    interface View : BaseView<Presenter>{
        fun openRegister()
    }

    interface Presenter : BasePresenter<View> {
        fun registerClicked()
    }
}