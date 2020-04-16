package com.example.workoutapp.domain.showworkout

import com.example.workoutapp.domain.showworkout.GetWorkoutUseCase.Input
import com.example.workoutapp.domain.showworkout.GetWorkoutUseCase.Output
import com.example.workoutapp.domain.showworkout.GetWorkoutUseCase.Output.*
import com.example.workoutapp.domain.workout.WorkoutRepository
import io.reactivex.Single

class GetWorkoutUseCaseImpl(
    private val workoutRepository: WorkoutRepository
) : GetWorkoutUseCase {
    override fun execute(input: Input): Single<Output> {
        return workoutRepository.getAllWorkouts()
            .map { workouts ->
                if (workouts.isEmpty()) {
                    SuccessNoData(workouts)
                } else {
                    Success(workouts)
                }
            }
            .onErrorReturn { ErrorUnknown }
    }
}