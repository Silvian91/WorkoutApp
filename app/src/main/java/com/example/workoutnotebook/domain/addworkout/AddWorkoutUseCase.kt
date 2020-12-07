package com.example.workoutnotebook.domain.addworkout

import com.example.workoutnotebook.domain.addworkout.AddWorkoutUseCase.Input
import com.example.workoutnotebook.domain.addworkout.AddWorkoutUseCase.Output
import com.example.workoutnotebook.domain.common.BaseSingleUseCase
import com.example.workoutnotebook.domain.common.BaseUseCase
import com.example.workoutnotebook.domain.workout.model.WorkoutModel

interface AddWorkoutUseCase : BaseSingleUseCase<Input, Output> {

    data class Input(val workout: WorkoutModel) : BaseUseCase.Input

    sealed class Output : BaseUseCase.Output {

        data class Success(val workoutId: Long) : Output()
        object ErrorUnknown : Output()
    }
}