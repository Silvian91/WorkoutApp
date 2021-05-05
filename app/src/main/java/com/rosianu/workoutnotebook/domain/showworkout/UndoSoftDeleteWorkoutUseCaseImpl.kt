package com.rosianu.workoutnotebook.domain.showworkout

import com.rosianu.workoutnotebook.domain.showworkout.UndoSoftDeleteWorkoutUseCase.Input
import com.rosianu.workoutnotebook.domain.showworkout.UndoSoftDeleteWorkoutUseCase.Output
import com.rosianu.workoutnotebook.domain.showworkout.UndoSoftDeleteWorkoutUseCase.Output.ErrorUnknown
import com.rosianu.workoutnotebook.domain.workout.WorkoutRepository
import io.reactivex.Single

class UndoSoftDeleteWorkoutUseCaseImpl(
    private val workoutRepository: WorkoutRepository
) : UndoSoftDeleteWorkoutUseCase {
    override fun execute(input: Input): Single<Output> {
        return workoutRepository.undoSoftDeleteWorkout(input.workoutId)
            .andThen(Single.just(Output.Success as Output))
            .onErrorReturn { ErrorUnknown }
    }
}