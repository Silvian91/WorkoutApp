package com.example.workoutnotebook.ui.copyworkout.adapter

import com.example.core.recyclerView.ItemWrapper
import com.example.workoutnotebook.domain.workout.model.WorkoutModel
import com.example.workoutnotebook.ui.copyworkout.adapter.WorkoutItemWrapper.ItemType.WORKOUT_NO_DATA
import com.example.workoutnotebook.ui.copyworkout.adapter.WorkoutItemWrapper.ItemType.WORKOUT_TITLE

sealed class WorkoutItemWrapper(val type: ItemType) : ItemWrapper {

    override fun getItemType(): Int {
        return type.ordinal
    }

    enum class ItemType {
        WORKOUT_TITLE,
        WORKOUT_NO_DATA
    }

    data class WorkoutTitle(
        val workout: WorkoutModel,
        val checked: Boolean,
        val checkChangeListener: () -> Unit
    ) :
        WorkoutItemWrapper(WORKOUT_TITLE)

    object WorkoutNoData : WorkoutItemWrapper(WORKOUT_NO_DATA)

}