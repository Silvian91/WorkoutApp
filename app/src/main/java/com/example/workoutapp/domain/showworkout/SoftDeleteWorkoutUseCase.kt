package com.example.workoutapp.domain.showworkout

import com.example.workoutapp.domain.common.BaseSingleUseCase
import com.example.workoutapp.domain.common.BaseUseCase
import com.example.workoutapp.domain.showworkout.SoftDeleteWorkoutUseCase.Input
import com.example.workoutapp.domain.showworkout.SoftDeleteWorkoutUseCase.Output

interface SoftDeleteWorkoutUseCase : BaseSingleUseCase<Input, Output> {

    data class Input(val workoutId: Long) : BaseUseCase.Input

    sealed class Output : BaseUseCase.Output {
        object Success : Output()
        object ErrorUnknown : Output()
    }
}