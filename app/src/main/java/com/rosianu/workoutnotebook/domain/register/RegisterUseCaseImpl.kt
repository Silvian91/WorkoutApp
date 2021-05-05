package com.rosianu.workoutnotebook.domain.register

import com.rosianu.workoutnotebook.domain.register.RegisterUseCase.Input
import com.rosianu.workoutnotebook.domain.register.RegisterUseCase.Output
import com.rosianu.workoutnotebook.domain.register.RegisterUseCase.Output.ErrorRegistrationFailed
import com.rosianu.workoutnotebook.domain.register.RegisterUseCase.Output.Success
import com.rosianu.workoutnotebook.domain.user.UserRepository
import io.reactivex.Single

class RegisterUseCaseImpl(
    private val userRepository: UserRepository
) : RegisterUseCase {
    override fun execute(input: Input): Single<Output> {
        return userRepository.insertUser(input.user)
            .andThen(Single.just(Success as Output))
            .onErrorReturn { ErrorRegistrationFailed }
    }
}