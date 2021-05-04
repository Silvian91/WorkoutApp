package com.example.workoutnotebook.domain.showroutine

import com.example.workoutnotebook.domain.common.BaseSingleUseCase
import com.example.workoutnotebook.domain.common.BaseUseCase
import com.example.workoutnotebook.domain.routine.model.RoutineModel
import com.example.workoutnotebook.domain.showroutine.GetRoutineUseCase.Input
import com.example.workoutnotebook.domain.showroutine.GetRoutineUseCase.Output

interface GetRoutineUseCase : BaseSingleUseCase<Input, Output> {

    data class Input(val workoutId: Long) : BaseUseCase.Input

    sealed class Output : BaseUseCase.Output {

        data class Success(val routines: List<RoutineModel>) : Output()
        object ErrorNoRoutines : Output()
        object ErrorUnknown: Output()
    }
}