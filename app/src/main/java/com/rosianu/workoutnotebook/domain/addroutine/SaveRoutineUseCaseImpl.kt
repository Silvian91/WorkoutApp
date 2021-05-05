package com.rosianu.workoutnotebook.domain.addroutine

import com.rosianu.workoutnotebook.domain.addroutine.SaveRoutineUseCase.Input
import com.rosianu.workoutnotebook.domain.addroutine.SaveRoutineUseCase.Output
import com.rosianu.workoutnotebook.domain.addroutine.SaveRoutineUseCase.Output.ErrorUnknown
import com.rosianu.workoutnotebook.domain.addroutine.SaveRoutineUseCase.Output.Success
import com.rosianu.workoutnotebook.domain.routine.RoutineRepository
import io.reactivex.Single

class SaveRoutineUseCaseImpl(
    private val routineRepository: RoutineRepository
) : SaveRoutineUseCase {
    override fun execute(input: Input): Single<Output> {
        return routineRepository.insertRoutine(input.routine)
            .andThen(Single.just(Success as Output))
            .onErrorReturn { ErrorUnknown }
    }
}