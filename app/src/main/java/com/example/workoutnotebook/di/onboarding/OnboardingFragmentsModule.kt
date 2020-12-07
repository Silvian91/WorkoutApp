package com.example.workoutnotebook.di.onboarding

import com.example.workoutnotebook.di.FragmentScope
import com.example.workoutnotebook.ui.onboarding.OnboardingFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface OnboardingFragmentsModule {

    @FragmentScope
    @ContributesAndroidInjector
    fun contributeOnboardingFragment(): OnboardingFragment
}