package com.example.workoutnotebook.domain.addroutine

import com.example.workoutnotebook.domain.addroutine.SaveRoutineUseCase.Input
import com.example.workoutnotebook.domain.addroutine.SaveRoutineUseCase.Output
import com.example.workoutnotebook.domain.common.BaseSingleUseCase
import com.example.workoutnotebook.domain.common.BaseUseCase
import com.example.workoutnotebook.domain.routine.model.RoutineModel

interface SaveRoutineUseCase : BaseSingleUseCase<Input, Output> {

    data class Input(val routine: List<RoutineModel>) : BaseUseCase.Input

    sealed class Output : BaseUseCase.Output {

        object Success : Output()
        object ErrorUnknown : Output()
    }
}