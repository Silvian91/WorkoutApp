package com.example.workoutnotebook.domain.showworkout

import com.example.workoutnotebook.domain.showworkout.SoftDeleteWorkoutUseCase.Input
import com.example.workoutnotebook.domain.showworkout.SoftDeleteWorkoutUseCase.Output
import com.example.workoutnotebook.domain.showworkout.SoftDeleteWorkoutUseCase.Output.ErrorUnknown
import com.example.workoutnotebook.domain.showworkout.SoftDeleteWorkoutUseCase.Output.Success
import com.example.workoutnotebook.domain.workout.WorkoutRepository
import io.reactivex.Single

class SoftDeleteWorkoutUseCaseImpl(
    private val workoutRepository: WorkoutRepository
) : SoftDeleteWorkoutUseCase {
    override fun execute(input: Input): Single<Output> {
        return workoutRepository.softDeleteWorkout(input.workoutId)
            .andThen(Single.just(Success as Output))
            .onErrorReturn { ErrorUnknown }
    }
}