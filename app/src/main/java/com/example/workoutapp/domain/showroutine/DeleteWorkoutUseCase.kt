package com.example.workoutapp.domain.showroutine

import com.example.workoutapp.domain.common.BaseSingleUseCase
import com.example.workoutapp.domain.common.BaseUseCase
import com.example.workoutapp.domain.showroutine.DeleteWorkoutUseCase.Input
import com.example.workoutapp.domain.showroutine.DeleteWorkoutUseCase.Output

interface DeleteWorkoutUseCase : BaseSingleUseCase<Input, Output> {

    data class Input(val workoutId: Long) : BaseUseCase.Input

    sealed class Output : BaseUseCase.Output {
        object Success : Output()
        object ErrorNotDeleted : Output()
    }
}