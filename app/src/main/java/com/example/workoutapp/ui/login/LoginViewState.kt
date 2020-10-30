package com.example.workoutapp.ui.login

import com.example.workoutapp.ui.error.UIError

data class LoginViewState (
    val home: Boolean,
    val register: Boolean,
    val showError: Boolean,
    val errorType: UIError.UIErrorFeature? = null
)