package com.example.workoutapp.di.splash

import com.example.workoutapp.domain.user.IsUserDBEmptyUseCase
import com.example.workoutapp.domain.user.IsUserDBEmptyUseCaseImpl
import com.example.workoutapp.domain.user.UserRepository
import dagger.Module
import dagger.Provides

@Module
class SplashModule {



    @Provides
    fun providesGetExistingUserUseCase(
        userRepository: UserRepository
    ): IsUserDBEmptyUseCase {
        return IsUserDBEmptyUseCaseImpl(userRepository)
    }

}