package com.example.workoutapp.di

import android.app.Application

interface DaggerComponentBuilder {
    fun build(application: Application): AppComponent
}