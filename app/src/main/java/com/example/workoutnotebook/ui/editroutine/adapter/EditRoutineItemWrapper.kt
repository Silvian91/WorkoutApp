package com.example.workoutnotebook.ui.editroutine.adapter

import com.example.workoutnotebook.domain.routine.model.RoutineModel

sealed class EditRoutineItemWrapper(
    val type: ItemType,
) {

    enum class ItemType {
        ROUTINE
    }

    data class Routine(val routine: RoutineModel) : EditRoutineItemWrapper(ItemType.ROUTINE)

}