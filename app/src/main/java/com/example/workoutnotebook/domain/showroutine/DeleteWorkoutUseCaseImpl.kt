package com.example.workoutnotebook.domain.showroutine

import com.example.workoutnotebook.domain.showroutine.DeleteWorkoutUseCase.Input
import com.example.workoutnotebook.domain.showroutine.DeleteWorkoutUseCase.Output
import com.example.workoutnotebook.domain.showroutine.DeleteWorkoutUseCase.Output.ErrorNotDeleted
import com.example.workoutnotebook.domain.showroutine.DeleteWorkoutUseCase.Output.Success
import com.example.workoutnotebook.domain.workout.WorkoutRepository
import io.reactivex.Single

class DeleteWorkoutUseCaseImpl(
    private val workoutRepository: WorkoutRepository
) : DeleteWorkoutUseCase {
    override fun execute(input: Input): Single<Output> {
        return workoutRepository.deleteWorkout(input.workoutId)
            .andThen(Single.just(Success as Output))
            .onErrorReturn { ErrorNotDeleted }
    }
}