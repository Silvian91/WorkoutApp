package com.example.workoutapp.addworkout

import com.example.workoutapp.common.BasePresenter
import com.example.workoutapp.common.BaseView

interface AddWorkoutContract {
    interface View : BaseView<Presenter> {
        fun showError()

        fun showAddRoutine(workoutId: Long)
    }

    interface Presenter : BasePresenter<View> {
        fun onConfirmClicked(workoutTitleField: String)
    }

}