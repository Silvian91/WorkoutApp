package com.example.workoutapp.di.login

import com.example.workoutapp.ui.loginactivity.LoginContract
import com.example.workoutapp.ui.loginactivity.LoginPresenter
import dagger.Module
import dagger.Provides

@Module
class LoginModule {

    @Provides
    fun providesLoginPresenter(): LoginContract.Presenter{
        return LoginPresenter()
    }
}