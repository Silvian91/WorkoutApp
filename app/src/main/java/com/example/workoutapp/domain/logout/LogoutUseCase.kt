package com.example.workoutapp.domain.logout

import com.example.workoutapp.domain.common.BaseSingleUseCase
import com.example.workoutapp.domain.common.BaseUseCase
import com.example.workoutapp.domain.logout.LogoutUseCase.Input
import com.example.workoutapp.domain.logout.LogoutUseCase.Output

interface LogoutUseCase : BaseSingleUseCase<Input, Output>{

    object Input : BaseUseCase.Input

    sealed class Output : BaseUseCase.Output {

        object Success: Output()
        object ErrorUnknown: Output()
    }
}