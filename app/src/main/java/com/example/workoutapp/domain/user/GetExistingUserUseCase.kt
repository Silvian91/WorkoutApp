package com.example.workoutapp.domain.user

import com.example.workoutapp.domain.common.BaseSingleUseCase
import com.example.workoutapp.domain.common.BaseUseCase
import com.example.workoutapp.domain.user.GetExistingUserUseCase.Input
import com.example.workoutapp.domain.user.GetExistingUserUseCase.Output

interface GetExistingUserUseCase : BaseSingleUseCase<Input, Output> {

    object Input : BaseUseCase.Input

    sealed class Output : BaseUseCase.Output {

        object Success: Output()
        object NoUsers: Output()
        object ErrorUnknown: Output()
    }
}