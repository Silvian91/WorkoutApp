package com.example.workoutnotebook.domain.showworkout

import com.example.workoutnotebook.domain.common.BaseSingleUseCase
import com.example.workoutnotebook.domain.common.BaseUseCase
import com.example.workoutnotebook.domain.showworkout.GetWorkoutUseCase.Input
import com.example.workoutnotebook.domain.showworkout.GetWorkoutUseCase.Output
import com.example.workoutnotebook.domain.workout.model.WorkoutModel

interface GetWorkoutUseCase : BaseSingleUseCase<Input, Output> {

    data class Input(val userId: Long) : BaseUseCase.Input

    sealed class Output : BaseUseCase.Output {

        data class Success(val workouts: List<WorkoutModel>) : Output()
        object SuccessNoData : Output()
        object ErrorUnknown : Output()
    }
}