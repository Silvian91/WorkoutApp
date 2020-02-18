package com.example.workoutapp.di.showworkout

import android.content.Context
import com.example.workoutapp.data.workout.WorkoutLocalDataSource
import com.example.workoutapp.data.workout.WorkoutLocalDataSourceImpl
import com.example.workoutapp.data.workout.WorkoutRepository
import com.example.workoutapp.data.workout.WorkoutRepositoryImpl
import com.example.workoutapp.showworkout.ShowWorkoutContract
import com.example.workoutapp.showworkout.ShowWorkoutPresenter
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable

@Module
class ShowWorkoutModule {
    @Provides
    fun providesShowWorkoutPresenter(
        workoutRepository: WorkoutRepository,
        compositeDisposable: CompositeDisposable
    ): ShowWorkoutContract.Presenter {
        return ShowWorkoutPresenter(workoutRepository, compositeDisposable)
    }

    @Provides
    fun providesWorkoutRepository(workoutLocalDataSource: WorkoutLocalDataSource): WorkoutRepository {
        return WorkoutRepositoryImpl(workoutLocalDataSource)
    }

    @Provides
    fun providesWorkoutLocalDataSource(context: Context): WorkoutLocalDataSource {
        return WorkoutLocalDataSourceImpl(context)
    }
}