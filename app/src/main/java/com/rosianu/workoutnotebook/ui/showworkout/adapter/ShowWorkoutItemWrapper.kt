package com.rosianu.workoutnotebook.ui.showworkout.adapter

import com.rosianu.workoutnotebook.domain.workout.model.WorkoutModel
import com.rosianu.workoutnotebook.ui.showworkout.adapter.ShowWorkoutItemWrapper.ItemType.WORKOUT_NO_DATA
import com.rosianu.workoutnotebook.ui.showworkout.adapter.ShowWorkoutItemWrapper.ItemType.WORKOUT_TITLE

sealed class ShowWorkoutItemWrapper(
    val type: ItemType
) {
    enum class ItemType {
        WORKOUT_TITLE,
        WORKOUT_NO_DATA
    }

    data class WorkoutTitle(val workoutTitle: WorkoutModel) :
        ShowWorkoutItemWrapper(WORKOUT_TITLE)

    object WorkoutNoData : ShowWorkoutItemWrapper(WORKOUT_NO_DATA)
}