package com.rosianu.workoutnotebook.domain.user

import com.rosianu.workoutnotebook.domain.common.BaseSingleUseCase
import com.rosianu.workoutnotebook.domain.common.BaseUseCase
import com.rosianu.workoutnotebook.domain.user.IsUserDBEmptyUseCase.Input
import com.rosianu.workoutnotebook.domain.user.IsUserDBEmptyUseCase.Output

interface IsUserDBEmptyUseCase : BaseSingleUseCase<Input, Output> {

    object Input : BaseUseCase.Input

    sealed class Output : BaseUseCase.Output {

        object DBEmpty: Output()
        object DBNotEmpty: Output()
        object ErrorUnknown: Output()
    }
}