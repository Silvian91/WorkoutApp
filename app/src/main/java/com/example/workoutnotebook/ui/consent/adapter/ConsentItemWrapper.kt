package com.example.workoutnotebook.ui.consent.adapter

import androidx.annotation.StringRes

sealed class ConsentItemWrapper(
    val type: ItemType
) {

    enum class ItemType {
        HEADER,
        BODY,
        ACTIONS
    }

    data class Header(@StringRes val header: Int) : ConsentItemWrapper(ItemType.HEADER)

    data class Body(@StringRes val body: Int) : ConsentItemWrapper(ItemType.BODY)

    object Actions : ConsentItemWrapper(ItemType.ACTIONS)

}