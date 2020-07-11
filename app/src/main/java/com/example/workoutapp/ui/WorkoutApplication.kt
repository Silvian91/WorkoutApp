package com.example.workoutapp.ui

import com.example.workoutapp.di.AppComponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

class WorkoutApplication : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return Dagger.factory(AppComponent.Factory::class.java).create(this)
    }

}