package com.example.workoutapp.ui.showworkout.adapter

import com.example.workoutapp.domain.workout.model.WorkoutModel
import com.example.workoutapp.ui.showworkout.adapter.ShowWorkoutItemWrapper.ItemType.WORKOUT_NO_DATA
import com.example.workoutapp.ui.showworkout.adapter.ShowWorkoutItemWrapper.ItemType.WORKOUT_TITLE

sealed class ShowWorkoutItemWrapper(
    val type: ItemType
) {
    enum class ItemType {
        WORKOUT_TITLE,
        WORKOUT_NO_DATA
    }

    data class WorkoutTitle(val workoutTitle: WorkoutModel) :
        ShowWorkoutItemWrapper(WORKOUT_TITLE)

    data class WorkoutNoData(val noWorkouts: WorkoutModel) : ShowWorkoutItemWrapper(WORKOUT_NO_DATA)
}