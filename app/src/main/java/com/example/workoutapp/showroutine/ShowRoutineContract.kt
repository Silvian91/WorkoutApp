package com.example.workoutapp.showroutine

import com.example.workoutapp.common.BasePresenter
import com.example.workoutapp.common.BaseView
import com.example.workoutapp.model.routine.RoutineEntity

interface ShowRoutineContract {

    interface View : BaseView<Presenter> {
        fun showRoutineData(routineData: List<RoutineEntity>)

        fun nextActivity()
    }

    interface Presenter : BasePresenter<View> {
        fun setWorkoutId(workoutId: Long)

        fun onDeleteClicked(workoutId: Long)
    }

}