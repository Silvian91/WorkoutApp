package com.example.workoutapp.di.onboarding

import com.example.workoutapp.di.ActivityScope
import com.example.workoutapp.di.FragmentScope
import com.example.workoutapp.ui.onboarding.OnboardingActivity
import com.example.workoutapp.ui.onboarding.OnboardingFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface OnboardingActivityModule {

    @ActivityScope
    @ContributesAndroidInjector(modules = [OnboardingFragmentsModule::class])
    fun provideOnboardingActivity(): OnboardingActivity

}