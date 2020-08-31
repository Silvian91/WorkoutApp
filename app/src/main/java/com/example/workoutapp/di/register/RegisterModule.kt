package com.example.workoutapp.di.register

import com.example.workoutapp.domain.register.RegisterUseCase
import com.example.workoutapp.domain.register.RegisterUseCaseImpl
import com.example.workoutapp.domain.user.UserRepository
import dagger.Module
import dagger.Provides

@Module
class RegisterModule {

    @Provides
    fun providesRegisterUseCase(userRepository: UserRepository): RegisterUseCase {
        return RegisterUseCaseImpl(userRepository)
    }

}