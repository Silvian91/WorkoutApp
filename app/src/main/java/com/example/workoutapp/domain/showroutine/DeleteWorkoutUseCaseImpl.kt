package com.example.workoutapp.domain.showroutine

import com.example.workoutapp.domain.showroutine.DeleteWorkoutUseCase.Input
import com.example.workoutapp.domain.showroutine.DeleteWorkoutUseCase.Output
import com.example.workoutapp.domain.showroutine.DeleteWorkoutUseCase.Output.ErrorNotDeleted
import com.example.workoutapp.domain.showroutine.DeleteWorkoutUseCase.Output.Success
import com.example.workoutapp.domain.workout.WorkoutRepository
import io.reactivex.Single

class DeleteWorkoutUseCaseImpl(
    private val workoutRepository: WorkoutRepository
) : DeleteWorkoutUseCase {
    override fun execute(input: Input): Single<Output> {
        return workoutRepository.deleteRoutine(input.workoutId)
            .andThen(Single.just(Success as Output))
            .onErrorReturn { ErrorNotDeleted }
    }
}