package com.example.workoutapp.domain.profile

import com.example.workoutapp.domain.profile.GetUserRoutinesUseCase.Input
import com.example.workoutapp.domain.profile.GetUserRoutinesUseCase.Output
import com.example.workoutapp.domain.profile.GetUserRoutinesUseCase.Output.*
import com.example.workoutapp.domain.routine.RoutineRepository
import io.reactivex.Single

class GetUserRoutinesUseCaseImpl(
    private val routineRepository: RoutineRepository
) : GetUserRoutinesUseCase {
    override fun execute(input: Input): Single<Output> {
        return routineRepository.getUserRoutine(input.userId)
            .map { routines ->
                if (routines.isEmpty()) {
                    SuccessNoRoutines
                } else {
                    Success(routines)
                }
            }
            .onErrorReturn { ErrorUnknown }
    }
}