package com.example.workoutapp.di.register

import com.example.workoutapp.ui.registeractivity.RegisterContract
import com.example.workoutapp.ui.registeractivity.RegisterPresenter
import dagger.Module
import dagger.Provides

@Module
class RegisterModule {

    @Provides
    fun providesRegisterPresenter(): RegisterContract.Presenter{
        return RegisterPresenter()
    }
}