package com.example.workoutnotebook.domain.user

import com.example.workoutnotebook.domain.common.BaseSingleUseCase
import com.example.workoutnotebook.domain.common.BaseUseCase
import com.example.workoutnotebook.domain.user.IsUserDBEmptyUseCase.Input
import com.example.workoutnotebook.domain.user.IsUserDBEmptyUseCase.Output

interface IsUserDBEmptyUseCase : BaseSingleUseCase<Input, Output> {

    object Input : BaseUseCase.Input

    sealed class Output : BaseUseCase.Output {

        object DBEmpty: Output()
        object DBNotEmpty: Output()
        object ErrorUnknown: Output()
    }
}