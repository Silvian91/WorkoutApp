package com.example.workoutapp.di.login

import android.content.Context
import com.example.workoutapp.data.user.UserLocalDataSource
import com.example.workoutapp.data.user.UserLocalDataSourceImpl
import com.example.workoutapp.data.user.UserRepositoryImpl
import com.example.workoutapp.domain.login.LoginUseCase
import com.example.workoutapp.domain.login.LoginUseCaseImpl
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
    fun providesLoginUseCase(userRepository: UserRepository): LoginUseCase{
        return LoginUseCaseImpl(userRepository)
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