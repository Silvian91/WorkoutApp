package com.example.workoutapp.di.splash

import android.content.Context
import com.example.workoutapp.data.user.UserLocalDataSource
import com.example.workoutapp.data.user.UserLocalDataSourceImpl
import com.example.workoutapp.data.user.UserRepositoryImpl
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
    fun providesSplashPresenter(
        isUserDBEmptyUseCase: IsUserDBEmptyUseCase,
        compositeDisposable: CompositeDisposable
    ):  {
        return SplashViewModel(isUserDBEmptyUseCase, compositeDisposable)
    }

    @Provides
    fun providesGetExistingUserUseCase(
        userRepository: UserRepository
    ): IsUserDBEmptyUseCase {
        return IsUserDBEmptyUseCaseImpl(userRepository)
    }

    @Provides
    fun providesUserRepository(
        userLocalDataSource: UserLocalDataSource
    ): UserRepository {
        return UserRepositoryImpl(userLocalDataSource)
    }

    @Provides
    fun providesUserLocalDataSource(context: Context): UserLocalDataSource {
        return UserLocalDataSourceImpl(context)
    }

}