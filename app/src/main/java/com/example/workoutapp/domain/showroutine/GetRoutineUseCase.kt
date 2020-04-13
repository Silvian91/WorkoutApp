package com.example.workoutapp.domain.showroutine

import com.example.workoutapp.domain.common.BaseSingleUseCase
import com.example.workoutapp.domain.common.BaseUseCase
import com.example.workoutapp.domain.routine.model.RoutineModel
import com.example.workoutapp.domain.showroutine.GetRoutineUseCase.Input
import com.example.workoutapp.domain.showroutine.GetRoutineUseCase.Output

interface GetRoutineUseCase : BaseSingleUseCase<Input, Output> {

    data class Input(val workoutId: Long) : BaseUseCase.Input

    sealed class Output : BaseUseCase.Output {

        data class Success(val routines: List<RoutineModel>) : Output()
        object ErrorNoRoutines : Output()
    }
}