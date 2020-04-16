package com.example.workoutapp.domain.addroutine

import com.example.workoutapp.domain.addroutine.SaveRoutineUseCase.Input
import com.example.workoutapp.domain.addroutine.SaveRoutineUseCase.Output
import com.example.workoutapp.domain.common.BaseSingleUseCase
import com.example.workoutapp.domain.common.BaseUseCase
import com.example.workoutapp.domain.routine.model.RoutineModel

interface SaveRoutineUseCase : BaseSingleUseCase<Input, Output> {

    data class Input(val routine: List<RoutineModel>) : BaseUseCase.Input

    sealed class Output : BaseUseCase.Output {

        object Success : Output()
        object ErrorUnknown : Output()
    }
}