package com.example.workoutapp.domain.showworkout

import com.example.workoutapp.domain.showworkout.DeleteWorkoutUseCase.Output
import com.example.workoutapp.domain.workout.WorkoutRepository
import io.reactivex.Single

class DeleteWorkoutUseCaseImpl(
    private val workoutRepository: WorkoutRepository
) : DeleteWorkoutUseCase {
    override fun execute(input: DeleteWorkoutUseCase.Input): Single<Output> {
        return workoutRepository.deleteRoutine(input.workoutId)
            .doOnComplete {
                if (input.workoutId ==
            }
    }
}