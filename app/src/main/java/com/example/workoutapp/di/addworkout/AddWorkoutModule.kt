package com.example.workoutapp.di.addworkout

import android.content.Context
import com.example.workoutapp.ui.addworkout.AddWorkoutContract
import com.example.workoutapp.ui.addworkout.AddWorkoutPresenter
import com.example.workoutapp.data.workout.WorkoutLocalDataSource
import com.example.workoutapp.data.workout.WorkoutLocalDataSourceImpl
import com.example.workoutapp.domain.workout.WorkoutRepository
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
        return AddWorkoutPresenter(compositeDisposable, workoutRepository)
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