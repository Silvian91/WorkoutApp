package com.example.workoutapp.ui.showworkout

import com.example.workoutapp.ui.common.BasePresenter
import com.example.workoutapp.ui.common.BaseView
import com.example.workoutapp.ui.showworkout.adapter.ShowWorkoutAdapter
import com.example.workoutapp.ui.showworkout.adapter.ShowWorkoutItemWrapper

interface ShowWorkoutContract {

    interface View : BaseView<Presenter> {
        fun showWorkouts(workoutsList: List<ShowWorkoutItemWrapper>)
        fun showRoutines(workoutId: Long)
        fun showError()
        fun showDeleteConfirmation(workoutId: Long)
        fun showUndoOption(workoutId: Long)
        fun showLogin()
    }

    interface Presenter : BasePresenter<View>,
        ShowWorkoutAdapter.WorkoutViewHolderListener {
        fun onDeleteClicked(workoutId: Long)
        fun onUndoDeletion(workoutId: Long)
    }
}