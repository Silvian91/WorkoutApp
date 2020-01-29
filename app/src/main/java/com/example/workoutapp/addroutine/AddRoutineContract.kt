package com.example.workoutapp.addroutine

import com.example.workoutapp.common.BasePresenter
import com.example.workoutapp.common.BaseView

interface AddRoutineContract {

    interface View : BaseView<Presenter> {
        fun clearAllInputFields()

        fun resetFocus()

        fun nextActivity()

        fun errorFieldEmpty(): String

        fun showError(errorType: ErrorType)
    }

    interface Presenter : BasePresenter<View> {
        fun setWorkoutId(workoutId: Long)

        fun onContinueClicked(
            routine_name: String,
            routine_sets: String,
            routine_reps: String,
            routine_weight: String,
            routine_rest: String
        )

        fun onFinishClicked(
            routine_name: String,
            routine_sets: String,
            routine_reps: String,
            routine_weight: String,
            routine_rest: String
        )

        fun onBackClicked(
            routine_name: String,
            routine_sets: String,
            routine_reps: String,
            routine_weight: String,
            routine_rest: String
        )
    }

    enum class ErrorType {
        NAME_EMPTY, SETS_EMPTY, REPS_EMPTY, WEIGHT_EMPTY, REST_EMPTY
    }


}