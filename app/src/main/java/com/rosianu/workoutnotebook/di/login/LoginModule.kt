package com.rosianu.workoutnotebook.di.login

import com.rosianu.workoutnotebook.domain.login.LoginUseCase
import com.rosianu.workoutnotebook.domain.login.LoginUseCaseImpl
import com.rosianu.workoutnotebook.domain.session.SessionManager
import com.rosianu.workoutnotebook.domain.user.UserRepository
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