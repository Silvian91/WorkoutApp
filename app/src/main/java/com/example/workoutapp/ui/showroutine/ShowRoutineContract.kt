package com.example.workoutapp.ui.showroutine

import com.example.workoutapp.ui.common.BasePresenter
import com.example.workoutapp.ui.common.BaseView
import com.example.workoutapp.ui.showroutine.adapter.ShowRoutineItemWrapper

interface ShowRoutineContract {

    interface View : BaseView<Presenter> {
        fun showRoutineData(routineData: List<ShowRoutineItemWrapper>)
        fun nextActivity()
        fun errorUnknown()
    }

    interface Presenter : BasePresenter<View> {
        fun setWorkoutId(workoutId: Long)
        fun onDeleteClicked(workoutId: Long)
    }

}