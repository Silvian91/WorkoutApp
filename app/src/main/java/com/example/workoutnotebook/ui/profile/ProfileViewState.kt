package com.example.workoutnotebook.ui.profile

import com.example.workoutnotebook.ui.profile.adapter.ProfileItemWrapper

data class ProfileViewState(
    val items: MutableList<ProfileItemWrapper>,
    val loading: Boolean,
    val login: Boolean,
    val bottomSheetDialog: Boolean,
    val cameraOpen: Boolean,
    val permissionCheck: Boolean,
    val galleryOpen: Boolean
)