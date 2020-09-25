package com.example.workoutapp.ui.profile.adapter

import android.graphics.Bitmap
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

sealed class ProfileItemWrapper(
    val type: ItemType
) {

    enum class ItemType {
        PROFILE,
        HEADING,
        WORKOUTS_COUNT,
        ROUTINES_COUNT
    }

    data class Profile(
        val username: String? = null,
        val profilePicture: Bitmap? = null,
        @DrawableRes val defaultUserPic: Int
    ) : ProfileItemWrapper(ItemType.PROFILE)

    data class Heading(@StringRes val header: Int) : ProfileItemWrapper(ItemType.HEADING)

    data class WorkoutsCount(val workoutsCount : Int? = 0) : ProfileItemWrapper(ItemType.WORKOUTS_COUNT)

    data class RoutinesCount(val routinesCount : Int? = 0) : ProfileItemWrapper(ItemType.ROUTINES_COUNT)

}