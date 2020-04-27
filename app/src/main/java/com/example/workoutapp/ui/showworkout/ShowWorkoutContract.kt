package com.example.workoutapp.ui.showworkout

import com.example.workoutapp.domain.workout.model.WorkoutModel
import com.example.workoutapp.ui.common.BasePresenter
import com.example.workoutapp.ui.common.BaseView
import com.example.workoutapp.ui.showworkout.adapter.ShowWorkoutItemWrapper
import com.example.workoutapp.ui.showworkout.adapter.ShowWorkoutRecyclerAdapter

interface ShowWorkoutContract {

    interface View : BaseView<Presenter> {
        fun showWorkoutsListData(workoutsList: List<ShowWorkoutItemWrapper>)
        fun showRoutines(workoutId: Long)
        fun showEmptyScreen()
        fun showError()
        fun alertDialog(workoutId: Long, workoutsList: List<ShowWorkoutItemWrapper>)
        fun deleteSnackbar(workoutId: Long, workoutsList: List<ShowWorkoutItemWrapper>)
    }

    interface Presenter : BasePresenter<View>,
        ShowWorkoutRecyclerAdapter.WorkoutViewHolderListener {
        fun onDeleteClicked(workoutId: Long, workoutsList: List<ShowWorkoutItemWrapper>)
    }
}