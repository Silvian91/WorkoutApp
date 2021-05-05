package com.rosianu.workoutnotebook.di.splash

import com.rosianu.workoutnotebook.domain.user.IsUserDBEmptyUseCase
import com.rosianu.workoutnotebook.domain.user.IsUserDBEmptyUseCaseImpl
import com.rosianu.workoutnotebook.domain.user.UserRepository
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