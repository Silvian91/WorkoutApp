package com.example.workoutapp.di.login

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

}