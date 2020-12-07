package com.example.workoutnotebook.domain.showworkout

import com.example.workoutnotebook.domain.common.BaseSingleUseCase
import com.example.workoutnotebook.domain.common.BaseUseCase
import com.example.workoutnotebook.domain.showworkout.SoftDeleteWorkoutUseCase.Input
import com.example.workoutnotebook.domain.showworkout.SoftDeleteWorkoutUseCase.Output

interface SoftDeleteWorkoutUseCase : BaseSingleUseCase<Input, Output> {

    data class Input(val workoutId: Long) : BaseUseCase.Input

    sealed class Output : BaseUseCase.Output {
        object Success : Output()
        object ErrorUnknown : Output()
    }
}