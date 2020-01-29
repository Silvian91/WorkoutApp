package com.example.workoutapp.di;

import android.app.Application;
import android.content.Context;

import dagger.Module;
import dagger.Provides;

@Module
public class AndroidModule {

    @Provides
    Context providesContext(Application application) {
        return application;
    }
}
