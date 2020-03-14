package com.example.workoutapp.ui.showworkout

import com.example.workoutapp.ui.common.BasePresenter
import com.example.workoutapp.ui.common.BaseView
import com.example.workoutapp.ui.showworkout.adapter.ShowWorkoutItemWrapper
import com.example.workoutapp.ui.showworkout.adapter.ShowWorkoutRecyclerAdapter

interface ShowWorkoutContract {

    interface View : BaseView<Presenter> {
        fun showWorkoutListData(workoutList: List<ShowWorkoutItemWrapper>)

        fun showRoutines(workoutId: Long)
    }

    interface Presenter : BasePresenter<View>,
        ShowWorkoutRecyclerAdapter.WorkoutViewHolderListener {

    }

}