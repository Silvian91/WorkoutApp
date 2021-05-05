package com.rosianu.workoutnotebook.ui.login

import com.rosianu.core.ui.error.UIError

data class LoginViewState (
    val home: Boolean,
    val register: Boolean,
    val showError: Boolean,
    val errorType: UIError.UIErrorFeature? = null
)