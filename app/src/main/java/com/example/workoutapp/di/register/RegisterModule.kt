package com.example.workoutapp.di.register

import com.example.workoutapp.domain.login.LoginUseCase
import com.example.workoutapp.domain.register.RegisterUseCase
import com.example.workoutapp.domain.register.RegisterUseCaseImpl
import com.example.workoutapp.domain.user.UserRepository
import com.example.workoutapp.ui.register.RegisterContract
import com.example.workoutapp.ui.register.RegisterPresenter
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable

@Module
class RegisterModule {

    @Provides
    fun providesRegisterPresenter(
        registerUseCase: RegisterUseCase,
        loginUseCase: LoginUseCase,
        compositeDisposable: CompositeDisposable
    ): RegisterContract.Presenter {
        return RegisterPresenter(registerUseCase, loginUseCase, compositeDisposable)
    }

    @Provides
    fun providesRegisterUseCase(userRepository: UserRepository): RegisterUseCase {
        return RegisterUseCaseImpl(userRepository)
    }

}