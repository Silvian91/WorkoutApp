package com.example.workoutnotebook.domain.profile

import com.example.workoutnotebook.domain.common.BaseSingleUseCase
import com.example.workoutnotebook.domain.common.BaseUseCase
import com.example.workoutnotebook.domain.profile.GetUserRoutinesUseCase.Input
import com.example.workoutnotebook.domain.profile.GetUserRoutinesUseCase.Output
import com.example.workoutnotebook.domain.routine.model.RoutineModel

interface GetUserRoutinesUseCase : BaseSingleUseCase<Input, Output>{

    data class Input(val userId: Long) : BaseUseCase.Input

    sealed class Output : BaseUseCase.Output {

        data class Success(val routines: List<RoutineModel>) : Output()
        object SuccessNoRoutines : Output()
        object ErrorUnknown : Output()
    }
}