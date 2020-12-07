package com.example.workoutnotebook.domain.addroutine

import com.example.workoutnotebook.domain.addroutine.DeleteRoutineUseCase.Input
import com.example.workoutnotebook.domain.addroutine.DeleteRoutineUseCase.Output
import com.example.workoutnotebook.domain.common.BaseSingleUseCase
import com.example.workoutnotebook.domain.common.BaseUseCase

interface DeleteRoutineUseCase : BaseSingleUseCase<Input, Output> {

    data class Input(val workoutId: Long) : BaseUseCase.Input

    sealed class Output : BaseUseCase.Output {

        object Success : Output()
        object ErrorUnknown : Output()
    }
}