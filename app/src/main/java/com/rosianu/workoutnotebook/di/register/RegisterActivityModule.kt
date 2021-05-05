package com.rosianu.workoutnotebook.di.register

import com.rosianu.workoutnotebook.di.ActivityScope
import com.rosianu.workoutnotebook.ui.register.RegisterActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface RegisterActivityModule {

    @ActivityScope
    @ContributesAndroidInjector
    fun provideRegisterActivity(): RegisterActivity

}