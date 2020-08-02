package com.example.workoutapp.domain.showworkout

import com.example.workoutapp.domain.showworkout.SoftDeleteWorkoutUseCase.Input
import com.example.workoutapp.domain.showworkout.SoftDeleteWorkoutUseCase.Output
import com.example.workoutapp.domain.showworkout.SoftDeleteWorkoutUseCase.Output.ErrorUnknown
import com.example.workoutapp.domain.showworkout.SoftDeleteWorkoutUseCase.Output.Success
import com.example.workoutapp.domain.workout.WorkoutRepository
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