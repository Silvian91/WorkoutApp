package com.example.workoutapp.di.profile

import com.example.workoutapp.domain.logout.LogoutUseCase
import com.example.workoutapp.domain.logout.LogoutUseCaseImpl
import com.example.workoutapp.domain.session.SessionManager
import dagger.Module
import dagger.Provides

@Module
class ProfileModule {

    @Provides
    fun providesLogoutUseCase(sessionManager: SessionManager): LogoutUseCase {
        return LogoutUseCaseImpl(sessionManager)
    }

}