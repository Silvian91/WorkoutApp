package com.rosianu.workoutnotebook.domain.showworkout

import com.rosianu.workoutnotebook.domain.common.BaseSingleUseCase
import com.rosianu.workoutnotebook.domain.common.BaseUseCase
import com.rosianu.workoutnotebook.domain.showworkout.GetWorkoutUseCase.Input
import com.rosianu.workoutnotebook.domain.showworkout.GetWorkoutUseCase.Output
import com.rosianu.workoutnotebook.domain.workout.model.WorkoutModel

interface GetWorkoutUseCase : BaseSingleUseCase<Input, Output> {

    data class Input(val userId: Long) : BaseUseCase.Input

    sealed class Output : BaseUseCase.Output {

        data class Success(val workouts: List<WorkoutModel>) : Output()
        object SuccessNoData : Output()
        object ErrorUnknown : Output()
    }
}