package com.example.workoutapp.ui.profile

import com.example.workoutapp.ui.common.BaseView

interface ProfileContract {

    interface View : BaseView<Presenter> {

    }

    interface Presenter : BaseView<View> {

    }
}