package com.example.workoutapp.di.login

import com.example.workoutapp.domain.login.LoginUseCase
import com.example.workoutapp.domain.login.LoginUseCaseImpl
import com.example.workoutapp.domain.session.SessionManager
import com.example.workoutapp.domain.user.UserRepository
import dagger.Module
import dagger.Provides

@Module
class LoginModule {

    @Provides
    fun providesLoginUseCase(
        userRepository: UserRepository,
        sessionManager: SessionManager
    ): LoginUseCase {
        return LoginUseCaseImpl(userRepository, sessionManager)
    }

}