package com.example.workoutnotebook.domain.logout

import com.example.workoutnotebook.domain.common.BaseSingleUseCase
import com.example.workoutnotebook.domain.common.BaseUseCase
import com.example.workoutnotebook.domain.logout.LogoutUseCase.Input
import com.example.workoutnotebook.domain.logout.LogoutUseCase.Output

interface LogoutUseCase : BaseSingleUseCase<Input, Output> {

    object Input : BaseUseCase.Input

    sealed class Output : BaseUseCase.Output {

        object Success : Output()
        object ErrorUnknown : Output()
    }
}