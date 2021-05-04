package com.example.workoutnotebook.domain.showroutine

import com.example.workoutnotebook.domain.routine.RoutineRepository
import com.example.workoutnotebook.domain.showroutine.GetRoutineUseCase.Input
import com.example.workoutnotebook.domain.showroutine.GetRoutineUseCase.Output
import com.example.workoutnotebook.domain.showroutine.GetRoutineUseCase.Output.*
import io.reactivex.Single
import timber.log.Timber

class GetRoutineUseCaseImpl(
    private val routineRepository: RoutineRepository
) : GetRoutineUseCase {
    override fun execute(input: Input): Single<Output> {
        return routineRepository.getRoutine(input.workoutId)
            .map { routines ->
                if (routines.isEmpty()) {
                    ErrorNoRoutines
                } else {
                    Success(routines)
                }
            }.onErrorReturn {
                Timber.e(it)
                ErrorUnknown
            }
    }
}