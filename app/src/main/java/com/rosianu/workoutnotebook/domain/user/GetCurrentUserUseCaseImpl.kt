package com.rosianu.workoutnotebook.domain.user

import com.rosianu.workoutnotebook.domain.session.SessionManager
import com.rosianu.workoutnotebook.domain.session.UnauthorizedException
import com.rosianu.workoutnotebook.domain.user.GetCurrentUserUseCase.Input
import com.rosianu.workoutnotebook.domain.user.GetCurrentUserUseCase.Output
import com.rosianu.workoutnotebook.domain.user.GetCurrentUserUseCase.Output.*
import io.reactivex.Single

class GetCurrentUserUseCaseImpl(
    private val sessionManager: SessionManager,
    private val userRepository: UserRepository
) : GetCurrentUserUseCase {
    override fun execute(input: Input): Single<Output> {
        return sessionManager.getCurrentUserId()
            .flatMap {
                userRepository.getUserCount(it)
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