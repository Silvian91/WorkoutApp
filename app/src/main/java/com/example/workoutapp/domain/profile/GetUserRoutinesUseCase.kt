package com.example.workoutapp.domain.profile

import com.example.workoutapp.domain.common.BaseSingleUseCase
import com.example.workoutapp.domain.common.BaseUseCase
import com.example.workoutapp.domain.profile.GetUserRoutinesUseCase.Input
import com.example.workoutapp.domain.profile.GetUserRoutinesUseCase.Output
import com.example.workoutapp.domain.routine.model.RoutineModel

interface GetUserRoutinesUseCase : BaseSingleUseCase<Input, Output>{

    data class Input(val userId: Long) : BaseUseCase.Input

    sealed class Output : BaseUseCase.Output {

        data class Success(val routines: List<RoutineModel>) : Output()
        object SuccessNoRoutines : Output()
        object ErrorUnknown : Output()
    }
}