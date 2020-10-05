package com.example.workoutapp.domain.login

import com.example.workoutapp.domain.common.BaseSingleUseCase
import com.example.workoutapp.domain.common.BaseUseCase
import com.example.workoutapp.domain.login.LoginUseCase.Input
import com.example.workoutapp.domain.login.LoginUseCase.Output

interface LoginUseCase : BaseSingleUseCase<Input, Output> {

    data class Input(val username: String, val password: String) : BaseUseCase.Input

    sealed class Output : BaseUseCase.Output {

        object Success : Output()
        object ErrorInvalidCredentials : Output()
        object ErrorUnknown : Output()
    }

}