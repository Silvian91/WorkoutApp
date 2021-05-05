package com.rosianu.workoutnotebook.domain.login

import com.rosianu.workoutnotebook.domain.common.BaseSingleUseCase
import com.rosianu.workoutnotebook.domain.common.BaseUseCase
import com.rosianu.workoutnotebook.domain.login.LoginUseCase.Input
import com.rosianu.workoutnotebook.domain.login.LoginUseCase.Output

interface LoginUseCase : BaseSingleUseCase<Input, Output> {

    data class Input(val username: String, val password: String) : BaseUseCase.Input

    sealed class Output : BaseUseCase.Output {

        object Success : Output()
        object ErrorInvalidCredentials : Output()
        object ErrorUnknown : Output()
    }

}