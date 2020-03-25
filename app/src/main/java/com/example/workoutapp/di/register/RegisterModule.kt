package com.example.workoutapp.di.register

import android.content.Context
import com.example.workoutapp.data.user.UserLocalDataSource
import com.example.workoutapp.data.user.UserLocalDataSourceImpl
import com.example.workoutapp.data.user.UserRepositoryImpl
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
        userRepository: UserRepository,
        compositeDisposable: CompositeDisposable
    ): RegisterContract.Presenter {
        return RegisterPresenter(userRepository, compositeDisposable)
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