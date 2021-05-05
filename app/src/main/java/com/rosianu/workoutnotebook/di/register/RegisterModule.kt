package com.rosianu.workoutnotebook.di.register

import com.rosianu.workoutnotebook.domain.register.RegisterUseCase
import com.rosianu.workoutnotebook.domain.register.RegisterUseCaseImpl
import com.rosianu.workoutnotebook.domain.user.UserRepository
import dagger.Module
import dagger.Provides

@Module
class RegisterModule {

    @Provides
    fun providesRegisterUseCase(userRepository: UserRepository): RegisterUseCase {
        return RegisterUseCaseImpl(userRepository)
    }

}