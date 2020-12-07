package com.example.workoutnotebook.domain.showroutine

import com.example.workoutnotebook.domain.routine.RoutineRepository
import com.example.workoutnotebook.domain.showroutine.GetRoutineUseCase.Input
import com.example.workoutnotebook.domain.showroutine.GetRoutineUseCase.Output
import com.example.workoutnotebook.domain.showroutine.GetRoutineUseCase.Output.ErrorNoRoutines
import com.example.workoutnotebook.domain.showroutine.GetRoutineUseCase.Output.Success
import io.reactivex.Single

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
            }
    }
}