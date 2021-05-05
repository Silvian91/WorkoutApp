package com.rosianu.workoutnotebook.domain.profile

import com.rosianu.workoutnotebook.domain.common.BaseSingleUseCase
import com.rosianu.workoutnotebook.domain.common.BaseUseCase
import com.rosianu.workoutnotebook.domain.profile.GetUserRoutinesUseCase.Input
import com.rosianu.workoutnotebook.domain.profile.GetUserRoutinesUseCase.Output
import com.rosianu.workoutnotebook.domain.routine.model.RoutineModel

interface GetUserRoutinesUseCase : BaseSingleUseCase<Input, Output>{

    data class Input(val userId: Long) : BaseUseCase.Input

    sealed class Output : BaseUseCase.Output {

        data class Success(val routines: List<RoutineModel>) : Output()
        object SuccessNoRoutines : Output()
        object ErrorUnknown : Output()
    }
}