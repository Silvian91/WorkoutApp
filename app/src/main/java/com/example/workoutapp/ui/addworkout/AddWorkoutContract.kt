package com.example.workoutapp.ui.addworkout

import com.example.workoutapp.ui.common.BasePresenter
import com.example.workoutapp.ui.common.BaseView

interface AddWorkoutContract {
    interface View : BaseView<Presenter> {
        fun showError()
        fun showAddRoutine(workoutId: Long)
        fun errorUnknown()
        fun showLogin()
    }

    interface Presenter : BasePresenter<View> {
        fun onConfirmClicked(workoutTitle: String)
    }

}