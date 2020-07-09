package com.example.workoutapp.ui.onboarding

import com.example.workoutapp.ui.common.BasePresenter
import com.example.workoutapp.ui.common.BaseView

interface OnboardingContract {

    interface View : BaseView<Presenter>{
        fun openRegister()
    }

    interface Presenter : BasePresenter<View> {
        fun registerClicked()
    }
}