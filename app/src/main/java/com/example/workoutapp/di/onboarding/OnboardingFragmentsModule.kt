package com.example.workoutapp.di.onboarding

import com.example.workoutapp.di.FragmentScope
import com.example.workoutapp.ui.onboarding.OnboardingFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface OnboardingFragmentsModule {

    @FragmentScope
    @ContributesAndroidInjector
    fun contributeOnboardingFragment(): OnboardingFragment
}