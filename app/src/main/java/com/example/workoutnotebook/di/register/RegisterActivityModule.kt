package com.example.workoutnotebook.di.register

import com.example.workoutnotebook.di.ActivityScope
import com.example.workoutnotebook.ui.register.RegisterActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface RegisterActivityModule {

    @ActivityScope
    @ContributesAndroidInjector
    fun provideRegisterActivity(): RegisterActivity

}