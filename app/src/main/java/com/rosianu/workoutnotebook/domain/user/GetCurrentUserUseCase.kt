package com.rosianu.workoutnotebook.domain.user

import com.rosianu.workoutnotebook.domain.common.BaseSingleUseCase
import com.rosianu.workoutnotebook.domain.common.BaseUseCase
import com.rosianu.workoutnotebook.domain.user.GetCurrentUserUseCase.Input
import com.rosianu.workoutnotebook.domain.user.GetCurrentUserUseCase.Output
import com.rosianu.workoutnotebook.domain.user.model.UserModel

interface GetCurrentUserUseCase : BaseSingleUseCase<Input, Output> {

    object Input : BaseUseCase.Input

    sealed class Output : BaseUseCase.Output {

        data class Success(val user: UserModel) : Output()
        object ErrorUnknown : Output()
        object ErrorUnauthorized : Output()

    }
}