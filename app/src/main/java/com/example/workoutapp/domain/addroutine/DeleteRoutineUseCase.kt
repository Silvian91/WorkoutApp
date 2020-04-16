package com.example.workoutapp.domain.addroutine

import com.example.workoutapp.domain.addroutine.DeleteRoutineUseCase.Input
import com.example.workoutapp.domain.addroutine.DeleteRoutineUseCase.Output
import com.example.workoutapp.domain.common.BaseSingleUseCase
import com.example.workoutapp.domain.common.BaseUseCase

interface DeleteRoutineUseCase : BaseSingleUseCase<Input, Output> {

    data class Input(val workoutId: Long) : BaseUseCase.Input

    sealed class Output : BaseUseCase.Output {

        object Success : Output()
        object ErrorUnknown : Output()
    }
}