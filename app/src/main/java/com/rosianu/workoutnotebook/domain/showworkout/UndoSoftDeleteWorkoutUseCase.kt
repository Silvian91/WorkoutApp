package com.rosianu.workoutnotebook.domain.showworkout

import com.rosianu.workoutnotebook.domain.common.BaseSingleUseCase
import com.rosianu.workoutnotebook.domain.common.BaseUseCase
import com.rosianu.workoutnotebook.domain.showworkout.UndoSoftDeleteWorkoutUseCase.Input
import com.rosianu.workoutnotebook.domain.showworkout.UndoSoftDeleteWorkoutUseCase.Output

interface UndoSoftDeleteWorkoutUseCase : BaseSingleUseCase<Input, Output> {

    data class Input(val workoutId: Long) : BaseUseCase.Input

    sealed class Output : BaseUseCase.Output {
        object Success : Output()
        object ErrorUnknown : Output()
    }
}