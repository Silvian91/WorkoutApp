package com.example.workoutapp.ui.profile

import com.example.workoutapp.ui.profile.adapter.ProfileItemWrapper

data class ProfileViewState(
    val items: MutableList<ProfileItemWrapper>,
    val login: Boolean,
    val bottomSheetDialog: Boolean,
    val cameraClicked: Boolean,
    val permissionCheck: Boolean,
    val galleryPermission: Boolean
)