package com.rosianu.workoutnotebook.di.onboarding

import com.rosianu.workoutnotebook.di.ActivityScope
import com.rosianu.workoutnotebook.ui.onboarding.OnboardingActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface OnboardingActivityModule {

    @ActivityScope
    @ContributesAndroidInjector(modules = [OnboardingFragmentsModule::class])
    fun provideOnboardingActivity(): OnboardingActivity

}