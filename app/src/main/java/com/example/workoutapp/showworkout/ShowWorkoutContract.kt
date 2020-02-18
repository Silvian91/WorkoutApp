package com.example.workoutapp.showworkout

import com.example.workoutapp.common.BasePresenter
import com.example.workoutapp.common.BaseView
import com.example.workoutapp.showworkout.adapter.ShowWorkoutItemWrapper
import com.example.workoutapp.showworkout.adapter.ShowWorkoutRecyclerAdapter

interface ShowWorkoutContract {

    interface View : BaseView<Presenter> {
        fun showWorkoutListData(workoutList: List<ShowWorkoutItemWrapper>)

        fun showRoutines(workoutId: Long)
    }

    interface Presenter : BasePresenter<View>,
        ShowWorkoutRecyclerAdapter.WorkoutViewHolderListener {

    }

}