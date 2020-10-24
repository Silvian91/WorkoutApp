package com.example.workoutapp.ui.consent.adapter

import androidx.annotation.StringRes

sealed class ConsentItemWrapper(
    val type: ItemType
) {

    enum class ItemType {
        HEADRER,
        BODY,
        ACTIONS
    }

    data class Header(@StringRes val header: Int) : ConsentItemWrapper(ItemType.HEADRER)

    data class Body(@StringRes val body: Int) : ConsentItemWrapper(ItemType.BODY)

    object Actions : ConsentItemWrapper(ItemType.ACTIONS)

}