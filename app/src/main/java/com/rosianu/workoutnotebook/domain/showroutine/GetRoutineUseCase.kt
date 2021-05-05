package com.rosianu.workoutnotebook.domain.showroutine

import com.rosianu.workoutnotebook.domain.common.BaseSingleUseCase
import com.rosianu.workoutnotebook.domain.common.BaseUseCase
import com.rosianu.workoutnotebook.domain.routine.model.RoutineModel
import com.rosianu.workoutnotebook.domain.showroutine.GetRoutineUseCase.Input
import com.rosianu.workoutnotebook.domain.showroutine.GetRoutineUseCase.Output

interface GetRoutineUseCase : BaseSingleUseCase<Input, Output> {

    data class Input(val workoutId: Long) : BaseUseCase.Input

    sealed class Output : BaseUseCase.Output {

        data class Success(val routines: List<RoutineModel>) : Output()
        object ErrorNoRoutines : Output()
        object ErrorUnknown: Output()
    }
}