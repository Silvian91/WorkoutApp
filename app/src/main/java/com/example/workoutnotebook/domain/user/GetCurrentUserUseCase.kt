package com.example.workoutnotebook.domain.user

import com.example.workoutnotebook.domain.common.BaseSingleUseCase
import com.example.workoutnotebook.domain.common.BaseUseCase
import com.example.workoutnotebook.domain.user.GetCurrentUserUseCase.Input
import com.example.workoutnotebook.domain.user.GetCurrentUserUseCase.Output
import com.example.workoutnotebook.domain.user.model.UserModel

interface GetCurrentUserUseCase : BaseSingleUseCase<Input, Output> {

    object Input : BaseUseCase.Input

    sealed class Output : BaseUseCase.Output {

        data class Success(val user: UserModel) : Output()
        object ErrorUnknown : Output()
        object ErrorUnauthorized : Output()

    }
}