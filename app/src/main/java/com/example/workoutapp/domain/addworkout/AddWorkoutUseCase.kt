package com.example.workoutapp.domain.addworkout

import com.example.workoutapp.domain.addworkout.AddWorkoutUseCase.Input
import com.example.workoutapp.domain.addworkout.AddWorkoutUseCase.Output
import com.example.workoutapp.domain.common.BaseSingleUseCase
import com.example.workoutapp.domain.common.BaseUseCase
import com.example.workoutapp.domain.workout.model.WorkoutModel

interface AddWorkoutUseCase : BaseSingleUseCase<Input, Output> {

    data class Input(val workout: WorkoutModel) : BaseUseCase.Input

    sealed class Output : BaseUseCase.Output {

        data class Success(val workoutId: Long) : Output()
        object ErrorUnknown : Output()
    }
}