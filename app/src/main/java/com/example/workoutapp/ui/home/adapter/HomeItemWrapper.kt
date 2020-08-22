package com.example.workoutapp.ui.home.adapter

sealed class HomeItemWrapper(
    val type: ItemType
) {

    enum class ItemType {
        ACTIONS
    }

    object Actions : HomeItemWrapper(ItemType.ACTIONS)

}