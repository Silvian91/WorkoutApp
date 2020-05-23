package com.example.workoutapp.di.profile

import android.content.Context
import android.content.SharedPreferences
import com.example.workoutapp.data.session.SessionKeyValueDataSource
import com.example.workoutapp.data.session.SessionKeyValueDataSourceImpl
import com.example.workoutapp.data.session.SessionManagerImpl
import com.example.workoutapp.data.user.UserLocalDataSource
import com.example.workoutapp.data.user.UserLocalDataSourceImpl
import com.example.workoutapp.data.user.UserRepositoryImpl
import com.example.workoutapp.domain.session.SessionManager
import com.example.workoutapp.domain.user.GetCurrentUserUseCase
import com.example.workoutapp.domain.user.GetCurrentUserUseCaseImpl
import com.example.workoutapp.domain.user.UserRepository
import com.example.workoutapp.ui.profile.ProfileContract
import com.example.workoutapp.ui.profile.ProfilePresenter
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable

@Module
class ProfileModule {

    @Provides
    fun providesProfilePresenter(
        getCurrentUserUseCase: GetCurrentUserUseCase,
        compositeDisposable: CompositeDisposable
    ): ProfileContract.Presenter {
        return ProfilePresenter(getCurrentUserUseCase, compositeDisposable)
    }

    @Provides
    fun providesGetCurrentUserUseCase(
        sessionManager: SessionManager,
        userRepository: UserRepository
    ): GetCurrentUserUseCase {
        return GetCurrentUserUseCaseImpl(sessionManager, userRepository)
    }

    @Provides
    fun providesSessionManager(sessionKeyValueDataSource: SessionKeyValueDataSource): SessionManager {
        return SessionManagerImpl(sessionKeyValueDataSource)
    }

    @Provides
    fun providesSessionKeyValueDataSource(sharedPreferences: SharedPreferences): SessionKeyValueDataSource {
        return SessionKeyValueDataSourceImpl(sharedPreferences)
    }

    @Provides
    fun providesUserRepository(userLocalDataSource: UserLocalDataSource): UserRepository {
        return UserRepositoryImpl(userLocalDataSource)
    }

    @Provides
    fun providesUserLocalDataSource(context: Context): UserLocalDataSource {
        return UserLocalDataSourceImpl(context)
    }

}