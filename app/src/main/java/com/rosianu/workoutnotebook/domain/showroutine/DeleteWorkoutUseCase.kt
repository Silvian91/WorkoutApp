package com.rosianu.workoutnotebook.domain.showroutine

import com.rosianu.workoutnotebook.domain.common.BaseSingleUseCase
import com.rosianu.workoutnotebook.domain.common.BaseUseCase
import com.rosianu.workoutnotebook.domain.showroutine.DeleteWorkoutUseCase.Input
import com.rosianu.workoutnotebook.domain.showroutine.DeleteWorkoutUseCase.Output

interface DeleteWorkoutUseCase : BaseSingleUseCase<Input, Output> {

    data class Input(val workoutId: Long) : BaseUseCase.Input

    sealed class Output : BaseUseCase.Output {
        object Success : Output()
        object ErrorNotDeleted : Output()
    }
}