package com.example.workoutapp.di.splash

import android.content.Context
import com.example.workoutapp.data.user.UserLocalDataSource
import com.example.workoutapp.data.user.UserLocalDataSourceImpl
import com.example.workoutapp.data.user.UserRepositoryImpl
import com.example.workoutapp.domain.user.GetExistingUserUseCase
import com.example.workoutapp.domain.user.GetExistingUserUseCaseImpl
import com.example.workoutapp.domain.user.UserRepository
import com.example.workoutapp.ui.splash.SplashContract
import com.example.workoutapp.ui.splash.SplashPresenter
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable

@Module
class SplashModule {

    @Provides
    fun providesSplashPresenter(
        getExistingUserUseCase: GetExistingUserUseCase,
        compositeDisposable: CompositeDisposable
    ): SplashContract.Presenter {
        return SplashPresenter(getExistingUserUseCase, compositeDisposable)
    }

    @Provides
    fun providesGetExistingUserUseCase(
        userRepository: UserRepository
    ): GetExistingUserUseCase {
        return GetExistingUserUseCaseImpl(userRepository)
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