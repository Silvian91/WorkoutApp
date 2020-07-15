package com.example.workoutapp.di.splash

import com.example.workoutapp.domain.user.IsUserDBEmptyUseCase
import com.example.workoutapp.domain.user.IsUserDBEmptyUseCaseImpl
import com.example.workoutapp.domain.user.UserRepository
import com.example.workoutapp.ui.splash.SplashViewModel
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable

@Module
class SplashModule {



    @Provides
    fun providesGetExistingUserUseCase(
        userRepository: UserRepository
    ): IsUserDBEmptyUseCase {
        return IsUserDBEmptyUseCaseImpl(userRepository)
    }

}