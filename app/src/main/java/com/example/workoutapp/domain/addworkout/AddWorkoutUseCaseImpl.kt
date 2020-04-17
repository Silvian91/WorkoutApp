package com.example.workoutapp.domain.addworkout

import com.example.workoutapp.domain.addworkout.AddWorkoutUseCase.Input
import com.example.workoutapp.domain.addworkout.AddWorkoutUseCase.Output
import com.example.workoutapp.domain.addworkout.AddWorkoutUseCase.Output.ErrorUnknown
import com.example.workoutapp.domain.addworkout.AddWorkoutUseCase.Output.Success
import com.example.workoutapp.domain.workout.WorkoutRepository
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