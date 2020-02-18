package com.example.workoutapp.showworkout.adapter

import com.example.workoutapp.domain.workout.model.WorkoutModel

sealed class ShowWorkoutItemWrapper(
    val type: ItemType
) {
    enum class ItemType {
        WORKOUT_TITLE
    }

    data class WorkoutTitle(val workoutTitle: WorkoutModel) :
        ShowWorkoutItemWrapper(ItemType.WORKOUT_TITLE)
}