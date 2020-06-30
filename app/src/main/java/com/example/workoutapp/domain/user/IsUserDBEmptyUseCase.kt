package com.example.workoutapp.domain.user

import com.example.workoutapp.domain.common.BaseSingleUseCase
import com.example.workoutapp.domain.common.BaseUseCase
import com.example.workoutapp.domain.user.IsUserDBEmptyUseCase.Input
import com.example.workoutapp.domain.user.IsUserDBEmptyUseCase.Output

interface IsUserDBEmptyUseCase : BaseSingleUseCase<Input, Output> {

    object Input : BaseUseCase.Input

    sealed class Output : BaseUseCase.Output {

        object DBEmpty: Output()
        object DBNotEmpty: Output()
        object ErrorUnknown: Output()
    }
}