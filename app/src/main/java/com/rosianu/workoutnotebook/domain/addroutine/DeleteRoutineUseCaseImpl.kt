package com.rosianu.workoutnotebook.domain.addroutine

import com.rosianu.workoutnotebook.domain.addroutine.DeleteRoutineUseCase.Input
import com.rosianu.workoutnotebook.domain.addroutine.DeleteRoutineUseCase.Output
import com.rosianu.workoutnotebook.domain.addroutine.DeleteRoutineUseCase.Output.ErrorUnknown
import com.rosianu.workoutnotebook.domain.addroutine.DeleteRoutineUseCase.Output.Success
import com.rosianu.workoutnotebook.domain.workout.WorkoutRepository
import io.reactivex.Single

class DeleteRoutineUseCaseImpl(
    private val workoutRepository: WorkoutRepository
) : DeleteRoutineUseCase {
    override fun execute(input: Input): Single<Output> {
        return workoutRepository.deleteWorkout(input.workoutId)
            .andThen(Single.just(Success as Output))
            .onErrorReturn { ErrorUnknown }
    }
}