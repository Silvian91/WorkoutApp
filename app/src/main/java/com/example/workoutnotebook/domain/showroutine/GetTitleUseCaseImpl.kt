package com.example.workoutnotebook.domain.showroutine

import com.example.workoutnotebook.domain.showroutine.GetTitleUseCase.Input
import com.example.workoutnotebook.domain.showroutine.GetTitleUseCase.Output
import com.example.workoutnotebook.domain.showroutine.GetTitleUseCase.Output.ErrorNoTitle
import com.example.workoutnotebook.domain.showroutine.GetTitleUseCase.Output.Success
import com.example.workoutnotebook.domain.workout.WorkoutRepository
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