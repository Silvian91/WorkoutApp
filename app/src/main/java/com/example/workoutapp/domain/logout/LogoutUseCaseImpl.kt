package com.example.workoutapp.domain.logout

import com.example.workoutapp.domain.logout.LogoutUseCase.Input
import com.example.workoutapp.domain.logout.LogoutUseCase.Output
import com.example.workoutapp.domain.logout.LogoutUseCase.Output.ErrorUnknown
import com.example.workoutapp.domain.logout.LogoutUseCase.Output.Success
import com.example.workoutapp.domain.session.SessionManager
import io.reactivex.Single

class LogoutUseCaseImpl(
    private val sessionManager: SessionManager
) : LogoutUseCase {
    override fun execute(input: Input): Single<Output> {
        //TODO: Add test to prove it's null!!
        return sessionManager.setCurrentUserId(null)
            .andThen(Single.just(Success as Output))
            .onErrorReturn { ErrorUnknown }
    }
}