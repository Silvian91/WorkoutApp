package com.example.workoutapp.domain.login

import com.example.workoutapp.domain.login.LoginUseCase.Input
import com.example.workoutapp.domain.login.LoginUseCase.Output
import com.example.workoutapp.domain.login.LoginUseCase.Output.*
import com.example.workoutapp.domain.user.UserRepository
import io.reactivex.Single

class LoginUseCaseImpl(
    private val userRepository: UserRepository
) : LoginUseCase {
    override fun execute(input: Input): Single<Output> {
        // GET THE USER BASED ON THE USERNAME PROVIDED BY THE INPUT
        return userRepository.getUser(input.username)
            // CHECK IF THE PASSWORD PROVIDED BY THE USER INSIDE THE INPUT OBJECT IS THE SAME AS THE PASSWORD RETRIEVED FROM THE DB
            .map {
                // YES -> RETURN SUCCESS / NOT -> INVALID CREDENTIALS
                if (input.password == it.password)
                    Success
                else
                    ErrorInvalidCredentials
            }
            // IF THE USER DOESN'T EXIST -> THE MAYBE RETUrNS EMPTY AND THEREFORE NEEDS TO BE SWITCHED OUT
            .switchIfEmpty(
                Single.just(ErrorUserDoesNotExist)
            )
            .onErrorReturn {
                ErrorUnknown
            }
    }
}