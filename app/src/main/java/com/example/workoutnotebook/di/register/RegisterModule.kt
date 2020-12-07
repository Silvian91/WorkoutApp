package com.example.workoutnotebook.di.register

import com.example.workoutnotebook.domain.register.RegisterUseCase
import com.example.workoutnotebook.domain.register.RegisterUseCaseImpl
import com.example.workoutnotebook.domain.user.UserRepository
import dagger.Module
import dagger.Provides

@Module
class RegisterModule {

    @Provides
    fun providesRegisterUseCase(userRepository: UserRepository): RegisterUseCase {
        return RegisterUseCaseImpl(userRepository)
    }

}