package com.example.workoutapp.di.addworkout

import android.content.Context
import com.example.workoutapp.addworkout.AddWorkoutContract
import com.example.workoutapp.addworkout.AddWorkoutPresenter
import com.example.workoutapp.data.workout.WorkoutLocalDataSource
import com.example.workoutapp.data.workout.WorkoutLocalDataSourceImpl
import com.example.workoutapp.data.workout.WorkoutRepository
import com.example.workoutapp.data.workout.WorkoutRepositoryImpl
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable

@Module
class AddWorkoutModule {
    @Provides
    fun providesAddWorkoutPresenter(
        workoutRepository: WorkoutRepository,
        compositeDisposable: CompositeDisposable
    ): AddWorkoutContract.Presenter {
        return AddWorkoutPresenter(workoutRepository, compositeDisposable)
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