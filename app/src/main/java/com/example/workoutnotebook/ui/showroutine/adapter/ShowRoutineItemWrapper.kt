package com.example.workoutnotebook.ui.showroutine.adapter

import com.example.workoutnotebook.domain.routine.model.RoutineModel

sealed class ShowRoutineItemWrapper(
    val type: ItemType,
    var isFirstItem: Boolean = false
) {

    enum class ItemType {
        TITLE,
        ENTRY
    }

    data class Title(val title: String) : ShowRoutineItemWrapper(ItemType.TITLE)

    data class Entry(val routine: RoutineModel) : ShowRoutineItemWrapper(ItemType.ENTRY)
}