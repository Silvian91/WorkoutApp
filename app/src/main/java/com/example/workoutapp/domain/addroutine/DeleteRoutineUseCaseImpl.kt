package com.example.workoutapp.domain.addroutine

import com.example.workoutapp.domain.addroutine.DeleteRoutineUseCase.Input
import com.example.workoutapp.domain.addroutine.DeleteRoutineUseCase.Output
import com.example.workoutapp.domain.addroutine.DeleteRoutineUseCase.Output.ErrorUnknown
import com.example.workoutapp.domain.addroutine.DeleteRoutineUseCase.Output.Success
import com.example.workoutapp.domain.workout.WorkoutRepository
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