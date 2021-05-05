package com.rosianu.workoutnotebook.domain.addroutine

import com.rosianu.workoutnotebook.domain.addroutine.SaveRoutineUseCase.Input
import com.rosianu.workoutnotebook.domain.addroutine.SaveRoutineUseCase.Output
import com.rosianu.workoutnotebook.domain.common.BaseSingleUseCase
import com.rosianu.workoutnotebook.domain.common.BaseUseCase
import com.rosianu.workoutnotebook.domain.routine.model.RoutineModel

interface SaveRoutineUseCase : BaseSingleUseCase<Input, Output> {

    data class Input(val routine: List<RoutineModel>) : BaseUseCase.Input

    sealed class Output : BaseUseCase.Output {

        object Success : Output()
        object ErrorUnknown : Output()
    }
}