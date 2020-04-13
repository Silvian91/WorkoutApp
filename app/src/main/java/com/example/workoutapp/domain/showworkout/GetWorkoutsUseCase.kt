package com.example.workoutapp.domain.showworkout

import com.example.workoutapp.domain.common.BaseSingleUseCase
import com.example.workoutapp.domain.common.BaseUseCase
import com.example.workoutapp.domain.showworkout.GetWorkoutsUseCase.Input
import com.example.workoutapp.domain.showworkout.GetWorkoutsUseCase.Output
import com.example.workoutapp.domain.workout.model.WorkoutModel
import com.example.workoutapp.ui.showworkout.adapter.ShowWorkoutItemWrapper
import com.example.workoutapp.ui.showworkout.adapter.ShowWorkoutItemWrapper.WorkoutNoData

interface GetWorkoutsUseCase : BaseSingleUseCase<Input, Output> {

    object Input : BaseUseCase.Input

    sealed class Output : BaseUseCase.Output {

        data class Success(val workouts: List<WorkoutModel>) : Output()
        data class SuccessNoData(val noWorkouts: List<WorkoutModel>) : Output()
        object ErrorUnknown : Output()
    }
}