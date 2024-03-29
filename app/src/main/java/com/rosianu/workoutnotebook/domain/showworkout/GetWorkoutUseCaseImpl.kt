package com.rosianu.workoutnotebook.domain.showworkout

import com.rosianu.workoutnotebook.domain.showworkout.GetWorkoutUseCase.Input
import com.rosianu.workoutnotebook.domain.showworkout.GetWorkoutUseCase.Output
import com.rosianu.workoutnotebook.domain.showworkout.GetWorkoutUseCase.Output.*
import com.rosianu.workoutnotebook.domain.workout.WorkoutRepository
import io.reactivex.Single

class GetWorkoutUseCaseImpl(
    private val workoutRepository: WorkoutRepository
) : GetWorkoutUseCase {
    override fun execute(input: Input): Single<Output> {
        return workoutRepository.getAllWorkouts(input.userId)
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