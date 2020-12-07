package com.example.workoutnotebook.di.splash

import com.example.workoutnotebook.domain.user.IsUserDBEmptyUseCase
import com.example.workoutnotebook.domain.user.IsUserDBEmptyUseCaseImpl
import com.example.workoutnotebook.domain.user.UserRepository
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