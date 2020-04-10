package com.example.workoutapp.domain.showworkout

import com.example.workoutapp.domain.showworkout.GetWorkoutsUseCase.Input
import com.example.workoutapp.domain.showworkout.GetWorkoutsUseCase.Output
import com.example.workoutapp.domain.showworkout.GetWorkoutsUseCase.Output.*
import com.example.workoutapp.domain.workout.WorkoutRepository
import io.reactivex.Single

class GetWorkoutsUseCaseImpl(
    private val workoutRepository: WorkoutRepository
) : GetWorkoutsUseCase {
    override fun execute(input: Input): Single<Output> {
        return workoutRepository.getAllWorkouts()
            .map { workouts ->
                if (workouts.isEmpty()) {
                    SuccessNoData
                } else {
                    Success(workouts)
                }
            }
            .onErrorReturn { ErrorUnknown }
    }
}