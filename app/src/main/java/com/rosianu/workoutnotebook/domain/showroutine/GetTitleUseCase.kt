package com.rosianu.workoutnotebook.domain.showroutine

import com.rosianu.workoutnotebook.domain.common.BaseSingleUseCase
import com.rosianu.workoutnotebook.domain.common.BaseUseCase
import com.rosianu.workoutnotebook.domain.showroutine.GetTitleUseCase.Input
import com.rosianu.workoutnotebook.domain.showroutine.GetTitleUseCase.Output
import com.rosianu.workoutnotebook.domain.workout.model.WorkoutModel

interface GetTitleUseCase : BaseSingleUseCase<Input, Output> {

    data class Input(val workoutId: Long) : BaseUseCase.Input

    sealed class Output : BaseUseCase.Output {

        data class Success(val workoutModel: List<WorkoutModel>) : Output()
        object ErrorNoTitle : Output()
    }
}