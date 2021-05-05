package com.rosianu.workoutnotebook.di.onboarding

import com.rosianu.workoutnotebook.di.FragmentScope
import com.rosianu.workoutnotebook.ui.onboarding.OnboardingFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface OnboardingFragmentsModule {

    @FragmentScope
    @ContributesAndroidInjector
    fun contributeOnboardingFragment(): OnboardingFragment
}