package com.example.workoutnotebook.domain.login

import com.example.workoutnotebook.domain.login.LoginUseCase.Input
import com.example.workoutnotebook.domain.login.LoginUseCase.Output
import com.example.workoutnotebook.domain.login.LoginUseCase.Output.*
import com.example.workoutnotebook.domain.session.SessionManager
import com.example.workoutnotebook.domain.user.UserRepository
import io.reactivex.Single

class LoginUseCaseImpl(
    private val userRepository: UserRepository,
    private val sessionManager: SessionManager
) : LoginUseCase {
    override fun execute(input: Input): Single<Output> {
        // GET THE USER BASED ON THE USERNAME PROVIDED BY THE INPUT
        return userRepository.getUserCount(input.username)
            // CHECK IF THE PASSWORD PROVIDED BY THE USER INSIDE THE INPUT OBJECT IS THE SAME AS THE PASSWORD RETRIEVED FROM THE DB
            .map {
                // YES -> RETURN SUCCESS / NOT -> INVALID CREDENTIALS
                if (input.password == it.password) {
                    sessionManager.setCurrentUserId(it.id!!).blockingGet()
                    Success
                } else
                    ErrorInvalidCredentials
            }
            // IF THE USER DOESN'T EXIST -> THE MAYBE RETUrNS EMPTY AND THEREFORE NEEDS TO BE SWITCHED OUT
            .switchIfEmpty(
                Single.just(ErrorInvalidCredentials)
            )
            .onErrorReturn {
                ErrorUnknown
            }
    }
}