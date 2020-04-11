package com.example.workoutapp.ui.showworkout

import com.example.workoutapp.ui.common.BasePresenter
import com.example.workoutapp.ui.common.BaseView
import com.example.workoutapp.ui.showworkout.adapter.ShowWorkoutItemWrapper.WorkoutNoData
import com.example.workoutapp.ui.showworkout.adapter.ShowWorkoutItemWrapper.WorkoutTitle
import com.example.workoutapp.ui.showworkout.adapter.ShowWorkoutRecyclerAdapter

interface ShowWorkoutContract {

    interface View : BaseView<Presenter> {
        fun showWorkoutsListData(workoutsList: List<WorkoutTitle>)
        fun showNoWorkoutsListData(noWorkouts: List<WorkoutNoData>)
        fun showRoutines(workoutId: Long)
        fun showEmptyScreen()
        fun showError()
    }

    interface Presenter : BasePresenter<View>,
        ShowWorkoutRecyclerAdapter.WorkoutViewHolderListener
}