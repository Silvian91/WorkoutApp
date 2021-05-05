package com.rosianu.workoutnotebook.ui.home.adapter

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

sealed class HomeItemWrapper(
    val type: ItemType
) {

    enum class ItemType {
        ACTION
    }

    data class Action(@DrawableRes val actionIconId: Int, @StringRes val actionTextId: Int) :
        HomeItemWrapper(ItemType.ACTION)

}