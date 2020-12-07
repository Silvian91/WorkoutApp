package com.example.workoutnotebook.di.profile

import com.example.workoutnotebook.domain.logout.LogoutUseCase
import com.example.workoutnotebook.domain.logout.LogoutUseCaseImpl
import com.example.workoutnotebook.domain.profile.GetUserRoutinesUseCase
import com.example.workoutnotebook.domain.profile.GetUserRoutinesUseCaseImpl
import com.example.workoutnotebook.domain.routine.RoutineRepository
import com.example.workoutnotebook.domain.session.SessionManager
import dagger.Module
import dagger.Provides

@Module
class ProfileModule {

    @Provides
    fun providesLogoutUseCase(sessionManager: SessionManager): LogoutUseCase {
        return LogoutUseCaseImpl(sessionManager)
    }

    @Provides
    fun providesGetUserRoutineUseCase(routineRepository: RoutineRepository): GetUserRoutinesUseCase {
        return GetUserRoutinesUseCaseImpl(routineRepository)
    }

}