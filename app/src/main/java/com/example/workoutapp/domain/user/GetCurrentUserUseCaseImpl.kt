package com.example.workoutapp.domain.user

import com.example.workoutapp.domain.session.SessionManager
import com.example.workoutapp.domain.session.UnauthorizedException
import com.example.workoutapp.domain.user.GetCurrentUserUseCase.Input
import com.example.workoutapp.domain.user.GetCurrentUserUseCase.Output
import com.example.workoutapp.domain.user.GetCurrentUserUseCase.Output.*
import io.reactivex.Single

class GetCurrentUserUseCaseImpl(
    private val sessionManager: SessionManager,
    private val userRepository: UserRepository
) : GetCurrentUserUseCase {
    override fun execute(input: Input): Single<Output> {
        return sessionManager.getCurrentUserId()
            .flatMap {
                userRepository.getUser(it)
            }
            .map {
                Success(it) as Output
            }
            .onErrorReturn {
                if (it is UnauthorizedException) {
                    ErrorUnauthorized
                } else {
                    ErrorUnknown
                }
            }

    }
}