package com.example.workoutapp.di.profile

import com.example.workoutapp.domain.logout.LogoutUseCase
import com.example.workoutapp.domain.logout.LogoutUseCaseImpl
import com.example.workoutapp.domain.profile.GetUserRoutinesUseCase
import com.example.workoutapp.domain.profile.GetUserRoutinesUseCaseImpl
import com.example.workoutapp.domain.routine.RoutineRepository
import com.example.workoutapp.domain.session.SessionManager
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