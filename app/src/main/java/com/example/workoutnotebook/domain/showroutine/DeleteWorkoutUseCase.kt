package com.example.workoutnotebook.domain.showroutine

import com.example.workoutnotebook.domain.common.BaseSingleUseCase
import com.example.workoutnotebook.domain.common.BaseUseCase
import com.example.workoutnotebook.domain.showroutine.DeleteWorkoutUseCase.Input
import com.example.workoutnotebook.domain.showroutine.DeleteWorkoutUseCase.Output

interface DeleteWorkoutUseCase : BaseSingleUseCase<Input, Output> {

    data class Input(val workoutId: Long) : BaseUseCase.Input

    sealed class Output : BaseUseCase.Output {
        object Success : Output()
        object ErrorNotDeleted : Output()
    }
}