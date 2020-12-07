package com.example.workoutnotebook.di.login

import com.example.workoutnotebook.domain.login.LoginUseCase
import com.example.workoutnotebook.domain.login.LoginUseCaseImpl
import com.example.workoutnotebook.domain.session.SessionManager
import com.example.workoutnotebook.domain.user.UserRepository
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