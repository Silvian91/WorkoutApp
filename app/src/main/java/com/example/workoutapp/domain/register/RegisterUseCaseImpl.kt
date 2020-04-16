package com.example.workoutapp.domain.register

import com.example.workoutapp.domain.register.RegisterUseCase.Output
import com.example.workoutapp.domain.register.RegisterUseCase.Output.ErrorRegistrationFailed
import com.example.workoutapp.domain.register.RegisterUseCase.Output.Success
import com.example.workoutapp.domain.user.UserRepository
import io.reactivex.Single

class RegisterUseCaseImpl(
    private val userRepository: UserRepository
) : RegisterUseCase {
    override fun execute(input: RegisterUseCase.Input): Single<Output> {
        return userRepository.insertUser(input.user)
            .andThen(Single.just(Success as Output))
            .onErrorReturn { ErrorRegistrationFailed }
    }
}