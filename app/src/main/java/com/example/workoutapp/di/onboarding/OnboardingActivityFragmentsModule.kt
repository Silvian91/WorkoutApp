package com.example.workoutapp.di.onboarding

import com.example.workoutapp.di.ActivityScope
import com.example.workoutapp.di.FragmentScope
import com.example.workoutapp.ui.onboarding.OnboardingActivity
import com.example.workoutapp.ui.onboarding.OnboardingFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface OnboardingActivityFragmentsModule {

    @ActivityScope
    @ContributesAndroidInjector
    fun provideOnboardingActivity(): OnboardingActivity


    @FragmentScope
    @ContributesAndroidInjector
    fun contributeOnboardingFragment(): OnboardingFragment

}