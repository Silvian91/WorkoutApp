package com.example.workoutapp.domain.register

import com.example.workoutapp.domain.common.BaseSingleUseCase
import com.example.workoutapp.domain.common.BaseUseCase
import com.example.workoutapp.domain.register.RegisterUseCase.Input
import com.example.workoutapp.domain.register.RegisterUseCase.Output
import com.example.workoutapp.domain.user.model.UserModel

interface RegisterUseCase : BaseSingleUseCase<Input, Output> {

    data class Input(val user: UserModel) : BaseUseCase.Input

    sealed class Output : BaseUseCase.Output {
        object Success : Output()
        object ErrorRegistrationFailed : Output()
    }
}