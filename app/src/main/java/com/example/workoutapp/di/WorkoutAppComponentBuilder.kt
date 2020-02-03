package com.example.workoutapp.di

import android.app.Application

class WorkoutAppComponentBuilder : DaggerComponentBuilder {
    override fun build(application: Application): AppComponent {
        return DaggerAppComponent.builder()
            .application(application)
            .build()
    }
}