package com.example.workoutapp.di.login

import android.content.Context
import android.content.SharedPreferences
import com.example.workoutapp.data.session.SessionKeyValueDataSource
import com.example.workoutapp.data.session.SessionKeyValueDataSourceImpl
import com.example.workoutapp.data.session.SessionManagerImpl
import com.example.workoutapp.data.user.UserLocalDataSource
import com.example.workoutapp.data.user.UserLocalDataSourceImpl
import com.example.workoutapp.data.user.UserRepositoryImpl
import com.example.workoutapp.domain.login.LoginUseCase
import com.example.workoutapp.domain.login.LoginUseCaseImpl
import com.example.workoutapp.domain.session.SessionManager
import com.example.workoutapp.domain.user.UserRepository
import com.example.workoutapp.ui.login.LoginContract
import com.example.workoutapp.ui.login.LoginPresenter
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable

@Module
class LoginModule {

    @Provides
    fun providesLoginPresenter(
        loginUseCase: LoginUseCase,
        compositeDisposable: CompositeDisposable
    ): LoginContract.Presenter {
        return LoginPresenter(loginUseCase, compositeDisposable)
    }

    @Provides
    fun providesLoginUseCase(
        userRepository: UserRepository,
        sessionManager: SessionManager
    ): LoginUseCase {
        return LoginUseCaseImpl(userRepository, sessionManager)
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