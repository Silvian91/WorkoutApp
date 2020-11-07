package com.example.workoutapp.di.app

import android.content.Context
import com.example.workoutapp.ui.WorkoutApplication
import dagger.Module
import dagger.Provides

@Module
class AppModule {

    @Provides
    fun providesContext(application: WorkoutApplication): Context {
        return application.applicationContext
    }

}