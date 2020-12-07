package com.example.workoutnotebook.di.addworkout

import android.content.Context
import android.content.SharedPreferences
import com.example.workoutnotebook.data.session.SessionKeyValueDataSource
import com.example.workoutnotebook.data.session.SessionKeyValueDataSourceImpl
import com.example.workoutnotebook.data.session.SessionManagerImpl
import com.example.workoutnotebook.data.user.UserLocalDataSource
import com.example.workoutnotebook.data.user.UserLocalDataSourceImpl
import com.example.workoutnotebook.data.user.UserRepositoryImpl
import com.example.workoutnotebook.domain.addworkout.AddWorkoutUseCase
import com.example.workoutnotebook.domain.addworkout.AddWorkoutUseCaseImpl
import com.example.workoutnotebook.domain.session.SessionManager
import com.example.workoutnotebook.domain.user.GetCurrentUserUseCase
import com.example.workoutnotebook.domain.user.GetCurrentUserUseCaseImpl
import com.example.workoutnotebook.domain.user.UserRepository
import com.example.workoutnotebook.domain.workout.WorkoutRepository
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