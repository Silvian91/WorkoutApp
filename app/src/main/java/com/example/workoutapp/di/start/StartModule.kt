package com.example.workoutapp.di.start

import com.example.workoutapp.ui.start.StartContract
import com.example.workoutapp.ui.start.StartPresenter
import dagger.Module
import dagger.Provides

@Module
class StartModule {

    @Provides
    fun providesStartPresenter() : StartContract.Presenter{
        return StartPresenter()
    }
}