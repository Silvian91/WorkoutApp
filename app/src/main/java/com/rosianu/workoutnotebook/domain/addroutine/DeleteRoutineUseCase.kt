package com.rosianu.workoutnotebook.domain.addroutine

import com.rosianu.workoutnotebook.domain.addroutine.DeleteRoutineUseCase.Input
import com.rosianu.workoutnotebook.domain.addroutine.DeleteRoutineUseCase.Output
import com.rosianu.workoutnotebook.domain.common.BaseSingleUseCase
import com.rosianu.workoutnotebook.domain.common.BaseUseCase

interface DeleteRoutineUseCase : BaseSingleUseCase<Input, Output> {

    data class Input(val workoutId: Long) : BaseUseCase.Input

    sealed class Output : BaseUseCase.Output {

        object Success : Output()
        object ErrorUnknown : Output()
    }
}