package com.rosianu.workoutnotebook.di.consent

import com.rosianu.workoutnotebook.di.ActivityScope
import com.rosianu.workoutnotebook.ui.consent.ConsentActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface ConsentActivityModule {

    @ActivityScope
    @ContributesAndroidInjector
    fun providesConsentActivity(): ConsentActivity
}