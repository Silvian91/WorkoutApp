package com.example.workoutnotebook.di.onboarding

import com.example.workoutnotebook.di.ActivityScope
import com.example.workoutnotebook.ui.onboarding.OnboardingActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface OnboardingActivityModule {

    @ActivityScope
    @ContributesAndroidInjector(modules = [OnboardingFragmentsModule::class])
    fun provideOnboardingActivity(): OnboardingActivity

}