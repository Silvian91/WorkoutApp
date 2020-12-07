package com.example.workoutnotebook.domain.addroutine

import com.example.workoutnotebook.domain.addroutine.SaveRoutineUseCase.Input
import com.example.workoutnotebook.domain.addroutine.SaveRoutineUseCase.Output
import com.example.workoutnotebook.domain.addroutine.SaveRoutineUseCase.Output.ErrorUnknown
import com.example.workoutnotebook.domain.addroutine.SaveRoutineUseCase.Output.Success
import com.example.workoutnotebook.domain.routine.RoutineRepository
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