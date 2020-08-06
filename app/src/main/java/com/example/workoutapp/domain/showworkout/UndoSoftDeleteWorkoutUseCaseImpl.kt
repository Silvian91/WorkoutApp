package com.example.workoutapp.domain.showworkout

import com.example.workoutapp.domain.showworkout.UndoSoftDeleteWorkoutUseCase.Input
import com.example.workoutapp.domain.showworkout.UndoSoftDeleteWorkoutUseCase.Output
import com.example.workoutapp.domain.showworkout.UndoSoftDeleteWorkoutUseCase.Output.ErrorUnknown
import com.example.workoutapp.domain.workout.WorkoutRepository
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