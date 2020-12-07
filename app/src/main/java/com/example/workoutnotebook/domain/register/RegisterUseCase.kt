package com.example.workoutnotebook.domain.register

import com.example.workoutnotebook.domain.common.BaseSingleUseCase
import com.example.workoutnotebook.domain.common.BaseUseCase
import com.example.workoutnotebook.domain.register.RegisterUseCase.Input
import com.example.workoutnotebook.domain.register.RegisterUseCase.Output
import com.example.workoutnotebook.domain.user.model.UserModel

interface RegisterUseCase : BaseSingleUseCase<Input, Output> {

    data class Input(val user: UserModel) : BaseUseCase.Input

    sealed class Output : BaseUseCase.Output {
        object Success : Output()
        object ErrorRegistrationFailed : Output()
    }
}