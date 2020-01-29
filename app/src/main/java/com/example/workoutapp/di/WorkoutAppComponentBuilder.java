package com.example.workoutapp.di;

import android.app.Application;
import org.jetbrains.annotations.NotNull;

public class WorkoutAppComponentBuilder implements DaggerComponentBuilder {
    @NotNull
    @Override
    public AppComponent build(@NotNull Application application) {
        return DaggerAppComponent.builder()
                .application(application)
                .build();
    }
}
