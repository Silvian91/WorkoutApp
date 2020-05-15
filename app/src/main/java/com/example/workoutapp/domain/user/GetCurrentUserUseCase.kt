package com.example.workoutapp.domain.user

import com.example.workoutapp.domain.common.BaseSingleUseCase
import com.example.workoutapp.domain.common.BaseUseCase
import com.example.workoutapp.domain.user.GetCurrentUserUseCase.Input
import com.example.workoutapp.domain.user.GetCurrentUserUseCase.Output
import com.example.workoutapp.domain.user.model.UserModel

interface GetCurrentUserUseCase : BaseSingleUseCase<Input, Output> {

    object Input : BaseUseCase.Input

    sealed class Output : BaseUseCase.Output {

        data class Success(val user: UserModel) : Output()
        object ErrorUnknown : Output()
        object ErrorUnauthorized : Output()

    }
}