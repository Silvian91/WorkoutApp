package com.example.workoutnotebook.domain.showroutine

import com.example.workoutnotebook.domain.common.BaseSingleUseCase
import com.example.workoutnotebook.domain.common.BaseUseCase
import com.example.workoutnotebook.domain.showroutine.GetTitleUseCase.Input
import com.example.workoutnotebook.domain.showroutine.GetTitleUseCase.Output
import com.example.workoutnotebook.domain.workout.model.WorkoutModel

interface GetTitleUseCase : BaseSingleUseCase<Input, Output> {

    data class Input(val workoutId: Long) : BaseUseCase.Input

    sealed class Output : BaseUseCase.Output {

        data class Success(val workoutModel: List<WorkoutModel>) : Output()
        object ErrorNoTitle : Output()
    }
}