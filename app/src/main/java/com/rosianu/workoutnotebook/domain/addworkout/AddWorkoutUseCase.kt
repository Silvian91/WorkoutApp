package com.rosianu.workoutnotebook.domain.addworkout

import com.rosianu.workoutnotebook.domain.addworkout.AddWorkoutUseCase.Input
import com.rosianu.workoutnotebook.domain.addworkout.AddWorkoutUseCase.Output
import com.rosianu.workoutnotebook.domain.common.BaseSingleUseCase
import com.rosianu.workoutnotebook.domain.common.BaseUseCase
import com.rosianu.workoutnotebook.domain.workout.model.WorkoutModel

interface AddWorkoutUseCase : BaseSingleUseCase<Input, Output> {

    data class Input(val workout: WorkoutModel) : BaseUseCase.Input

    sealed class Output : BaseUseCase.Output {

        data class Success(val workoutId: Long) : Output()
        object ErrorUnknown : Output()
    }
}