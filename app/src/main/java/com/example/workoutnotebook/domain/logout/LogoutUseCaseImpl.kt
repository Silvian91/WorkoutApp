package com.example.workoutnotebook.domain.logout

import com.example.workoutnotebook.domain.logout.LogoutUseCase.Input
import com.example.workoutnotebook.domain.logout.LogoutUseCase.Output
import com.example.workoutnotebook.domain.logout.LogoutUseCase.Output.ErrorUnknown
import com.example.workoutnotebook.domain.logout.LogoutUseCase.Output.Success
import com.example.workoutnotebook.domain.session.SessionManager
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