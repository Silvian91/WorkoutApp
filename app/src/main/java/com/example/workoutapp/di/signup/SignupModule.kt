package com.example.workoutapp.di.signup

import com.example.workoutapp.ui.signupactivity.SignupContract
import com.example.workoutapp.ui.signupactivity.SignupPresenter
import dagger.Module
import dagger.Provides

@Module
class SignupModule {

    @Provides
    fun providesSignupPresenter(): SignupContract.Presenter{
        return SignupPresenter()
    }
}