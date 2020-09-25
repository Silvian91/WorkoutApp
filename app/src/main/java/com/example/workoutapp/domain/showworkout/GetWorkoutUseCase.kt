package com.example.workoutapp.domain.showworkout

import com.example.workoutapp.domain.common.BaseSingleUseCase
import com.example.workoutapp.domain.common.BaseUseCase
import com.example.workoutapp.domain.showworkout.GetWorkoutUseCase.Input
import com.example.workoutapp.domain.showworkout.GetWorkoutUseCase.Output
import com.example.workoutapp.domain.workout.model.WorkoutModel

interface GetWorkoutUseCase : BaseSingleUseCase<Input, Output> {

    data class Input(val userId: Long) : BaseUseCase.Input

    sealed class Output : BaseUseCase.Output {

        data class Success(val workouts: List<WorkoutModel>) : Output()
        object SuccessNoData : Output()
        object ErrorUnknown : Output()
    }
}