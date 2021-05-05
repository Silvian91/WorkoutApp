package com.rosianu.workoutnotebook.di.addworkout

import android.content.Context
import android.content.SharedPreferences
import com.rosianu.workoutnotebook.data.session.SessionKeyValueDataSource
import com.rosianu.workoutnotebook.data.session.SessionKeyValueDataSourceImpl
import com.rosianu.workoutnotebook.data.session.SessionManagerImpl
import com.rosianu.workoutnotebook.data.user.UserLocalDataSource
import com.rosianu.workoutnotebook.data.user.UserLocalDataSourceImpl
import com.rosianu.workoutnotebook.data.user.UserRepositoryImpl
import com.rosianu.workoutnotebook.domain.addworkout.AddWorkoutUseCase
import com.rosianu.workoutnotebook.domain.addworkout.AddWorkoutUseCaseImpl
import com.rosianu.workoutnotebook.domain.session.SessionManager
import com.rosianu.workoutnotebook.domain.user.GetCurrentUserUseCase
import com.rosianu.workoutnotebook.domain.user.GetCurrentUserUseCaseImpl
import com.rosianu.workoutnotebook.domain.user.UserRepository
import com.rosianu.workoutnotebook.domain.workout.WorkoutRepository
import dagger.Module
import dagger.Provides

@Module
class AddWorkoutModule {

    @Provides
    fun providesAddWorkoutUseCase(workoutRepository: WorkoutRepository): AddWorkoutUseCase {
        return AddWorkoutUseCaseImpl(workoutRepository)
    }

    @Provides
    fun providesGetCurrentUserUseCase(
        sessionManager: SessionManager,
        userRepository: UserRepository
    ): GetCurrentUserUseCase {
        return GetCurrentUserUseCaseImpl(sessionManager, userRepository)
    }

    @Provides
    fun providesUserRepository(userLocalDataSource: UserLocalDataSource): UserRepository {
        return UserRepositoryImpl(userLocalDataSource)
    }

    @Provides
    fun providesUserLocalDataSource(context: Context): UserLocalDataSource {
        return UserLocalDataSourceImpl(context)
    }

    @Provides
    fun providesSessionManager(sessionKeyValueDataSource: SessionKeyValueDataSource): SessionManager {
        return SessionManagerImpl(sessionKeyValueDataSource)
    }

    @Provides
    fun providesSessionKeyValueDataSource(sharedPreferences: SharedPreferences): SessionKeyValueDataSource {
        return SessionKeyValueDataSourceImpl(sharedPreferences)
    }

}