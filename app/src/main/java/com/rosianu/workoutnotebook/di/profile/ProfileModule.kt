package com.rosianu.workoutnotebook.di.profile

import com.rosianu.workoutnotebook.domain.logout.LogoutUseCase
import com.rosianu.workoutnotebook.domain.logout.LogoutUseCaseImpl
import com.rosianu.workoutnotebook.domain.profile.GetUserRoutinesUseCase
import com.rosianu.workoutnotebook.domain.profile.GetUserRoutinesUseCaseImpl
import com.rosianu.workoutnotebook.domain.routine.RoutineRepository
import com.rosianu.workoutnotebook.domain.session.SessionManager
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