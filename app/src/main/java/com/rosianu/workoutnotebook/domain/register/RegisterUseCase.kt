package com.rosianu.workoutnotebook.domain.register

import com.rosianu.workoutnotebook.domain.common.BaseSingleUseCase
import com.rosianu.workoutnotebook.domain.common.BaseUseCase
import com.rosianu.workoutnotebook.domain.register.RegisterUseCase.Input
import com.rosianu.workoutnotebook.domain.register.RegisterUseCase.Output
import com.rosianu.workoutnotebook.domain.user.model.UserModel

interface RegisterUseCase : BaseSingleUseCase<Input, Output> {

    data class Input(val user: UserModel) : BaseUseCase.Input

    sealed class Output : BaseUseCase.Output {
        object Success : Output()
        object ErrorRegistrationFailed : Output()
    }
}