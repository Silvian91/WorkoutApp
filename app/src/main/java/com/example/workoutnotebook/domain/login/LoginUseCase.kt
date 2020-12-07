package com.example.workoutnotebook.domain.login

import com.example.workoutnotebook.domain.common.BaseSingleUseCase
import com.example.workoutnotebook.domain.common.BaseUseCase
import com.example.workoutnotebook.domain.login.LoginUseCase.Input
import com.example.workoutnotebook.domain.login.LoginUseCase.Output

interface LoginUseCase : BaseSingleUseCase<Input, Output> {

    data class Input(val username: String, val password: String) : BaseUseCase.Input

    sealed class Output : BaseUseCase.Output {

        object Success : Output()
        object ErrorInvalidCredentials : Output()
        object ErrorUnknown : Output()
    }

}