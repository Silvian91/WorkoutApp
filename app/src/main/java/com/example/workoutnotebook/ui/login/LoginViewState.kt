package com.example.workoutnotebook.ui.login

import com.example.core.ui.error.UIError

data class LoginViewState (
    val home: Boolean,
    val register: Boolean,
    val showError: Boolean,
    val errorType: UIError.UIErrorFeature? = null
)