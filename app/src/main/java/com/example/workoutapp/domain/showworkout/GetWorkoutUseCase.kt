package com.example.workoutapp.domain.showworkout

import com.example.workoutapp.domain.common.BaseSingleUseCase
import com.example.workoutapp.domain.common.BaseUseCase
import com.example.workoutapp.domain.showworkout.GetWorkoutUseCase.Input
import com.example.workoutapp.domain.showworkout.GetWorkoutUseCase.Output
import com.example.workoutapp.domain.workout.model.WorkoutModel

interface GetWorkoutUseCase : BaseSingleUseCase<Input, Output> {

    object Input : BaseUseCase.Input

    sealed class Output : BaseUseCase.Output {

        data class Success(val workouts: List<WorkoutModel>) : Output()
        data class SuccessNoData(val noWorkouts: List<WorkoutModel>) : Output()
        object ErrorUnknown : Output()
    }
}