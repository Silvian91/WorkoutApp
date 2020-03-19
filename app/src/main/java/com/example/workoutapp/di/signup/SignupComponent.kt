package com.example.workoutapp.di.signup

import com.example.workoutapp.ui.signup.SignupActivity
import dagger.Subcomponent

@Subcomponent(modules = [SignupModule::class])
interface SignupComponent {
    fun inject(signupActivity: SignupActivity)
}