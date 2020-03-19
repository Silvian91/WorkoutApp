package com.example.workoutapp.di.register

import com.example.workoutapp.ui.register.RegisterContract
import com.example.workoutapp.ui.register.RegisterPresenter
import dagger.Module
import dagger.Provides

@Module
class RegisterModule {

    @Provides
    fun providesRegisterPresenter(): RegisterContract.Presenter{
        return RegisterPresenter()
    }
}