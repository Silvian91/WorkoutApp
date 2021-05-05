package com.rosianu.workoutnotebook.domain.logout

import com.rosianu.workoutnotebook.domain.common.BaseSingleUseCase
import com.rosianu.workoutnotebook.domain.common.BaseUseCase
import com.rosianu.workoutnotebook.domain.logout.LogoutUseCase.Input
import com.rosianu.workoutnotebook.domain.logout.LogoutUseCase.Output

interface LogoutUseCase : BaseSingleUseCase<Input, Output> {

    object Input : BaseUseCase.Input

    sealed class Output : BaseUseCase.Output {

        object Success : Output()
        object ErrorUnknown : Output()
    }
}