package com.rosianu.workoutnotebook.domain.addworkout

import com.rosianu.workoutnotebook.domain.addworkout.AddWorkoutUseCase.Input
import com.rosianu.workoutnotebook.domain.addworkout.AddWorkoutUseCase.Output
import com.rosianu.workoutnotebook.domain.addworkout.AddWorkoutUseCase.Output.ErrorUnknown
import com.rosianu.workoutnotebook.domain.addworkout.AddWorkoutUseCase.Output.Success
import com.rosianu.workoutnotebook.domain.workout.WorkoutRepository
import io.reactivex.Single

class AddWorkoutUseCaseImpl(
    private val workoutRepository: WorkoutRepository
) : AddWorkoutUseCase {
    override fun execute(input: Input): Single<Output> {
        return workoutRepository.insertWorkout(input.workout)
            .map { Success(it) as Output }
            .onErrorReturn { ErrorUnknown }
    }
}