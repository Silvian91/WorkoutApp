package com.example.workoutnotebook.domain.profile

import com.example.workoutnotebook.domain.profile.GetUserRoutinesUseCase.Input
import com.example.workoutnotebook.domain.profile.GetUserRoutinesUseCase.Output
import com.example.workoutnotebook.domain.profile.GetUserRoutinesUseCase.Output.*
import com.example.workoutnotebook.domain.routine.RoutineRepository
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