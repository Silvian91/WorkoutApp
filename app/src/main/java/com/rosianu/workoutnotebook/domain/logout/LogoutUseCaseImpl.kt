package com.rosianu.workoutnotebook.domain.logout

import com.rosianu.workoutnotebook.domain.logout.LogoutUseCase.Input
import com.rosianu.workoutnotebook.domain.logout.LogoutUseCase.Output
import com.rosianu.workoutnotebook.domain.logout.LogoutUseCase.Output.ErrorUnknown
import com.rosianu.workoutnotebook.domain.logout.LogoutUseCase.Output.Success
import com.rosianu.workoutnotebook.domain.session.SessionManager
import io.reactivex.Single

class LogoutUseCaseImpl(
    private val sessionManager: SessionManager
) : LogoutUseCase {
    override fun execute(input: Input): Single<Output> {
        return sessionManager.setCurrentUserId(null)
            .andThen(Single.just(Success as Output))
            .onErrorReturn { ErrorUnknown }
    }
}