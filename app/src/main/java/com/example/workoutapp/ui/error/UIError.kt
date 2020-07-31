package com.example.workoutapp.ui.error

sealed class UIError {

    object ShowUnexpectedErrorSnackbar : UIError()

    object HideUnexpectedErrorSnackbar : UIError()

    abstract class UIErrorFeature : UIError()
}