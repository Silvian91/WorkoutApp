package com.rosianu.workoutnotebook.domain.showroutine

import com.rosianu.workoutnotebook.domain.showroutine.GetTitleUseCase.Input
import com.rosianu.workoutnotebook.domain.showroutine.GetTitleUseCase.Output
import com.rosianu.workoutnotebook.domain.showroutine.GetTitleUseCase.Output.ErrorNoTitle
import com.rosianu.workoutnotebook.domain.showroutine.GetTitleUseCase.Output.Success
import com.rosianu.workoutnotebook.domain.workout.WorkoutRepository
import io.reactivex.Single

class GetTitleUseCaseImpl(
    private val workoutRepository: WorkoutRepository
) : GetTitleUseCase {
    override fun execute(input: Input): Single<Output> {
        return workoutRepository.getWorkoutTitle(input.workoutId)
            .map {
                Success(it) as Output
            }
            .onErrorReturn {
                ErrorNoTitle
            }
    }
}