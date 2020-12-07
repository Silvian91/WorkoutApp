package com.example.workoutnotebook.domain.register

import com.example.workoutnotebook.domain.register.RegisterUseCase.Input
import com.example.workoutnotebook.domain.register.RegisterUseCase.Output
import com.example.workoutnotebook.domain.register.RegisterUseCase.Output.ErrorRegistrationFailed
import com.example.workoutnotebook.domain.register.RegisterUseCase.Output.Success
import com.example.workoutnotebook.domain.user.UserRepository
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