package com.example.workoutapp.di.start

import com.example.workoutapp.ui.onboarding.OnboardingActivity
import dagger.Subcomponent

@Subcomponent(modules = [StartModule::class])
interface StartComponent {
    fun inject(onboardingActivity: OnboardingActivity)
}