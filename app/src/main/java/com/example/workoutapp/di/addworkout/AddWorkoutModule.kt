package com.example.workoutapp.di.addworkout

import android.content.Context
import com.example.workoutapp.data.workout.WorkoutLocalDataSource
import com.example.workoutapp.data.workout.WorkoutLocalDataSourceImpl
import com.example.workoutapp.data.workout.WorkoutRepositoryImpl
import com.example.workoutapp.domain.addworkout.AddWorkoutUseCase
import com.example.workoutapp.domain.addworkout.AddWorkoutUseCaseImpl
import com.example.workoutapp.domain.workout.WorkoutRepository
import com.example.workoutapp.ui.addworkout.AddWorkoutContract
import com.example.workoutapp.ui.addworkout.AddWorkoutPresenter
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable

@Module
class AddWorkoutModule {
    @Provides
    fun providesAddWorkoutPresenter(
        addWorkoutUseCase: AddWorkoutUseCase,
        compositeDisposable: CompositeDisposable
    ): AddWorkoutContract.Presenter {
        return AddWorkoutPresenter(addWorkoutUseCase, compositeDisposable)
    }

    @Provides
    fun providesWorkoutRepository(workoutLocalDataSource: WorkoutLocalDataSource): WorkoutRepository {
        return WorkoutRepositoryImpl(workoutLocalDataSource)
    }

    @Provides
    fun providesWorkoutLocalDataSource(context: Context): WorkoutLocalDataSource {
        return WorkoutLocalDataSourceImpl(context)
    }

    @Provides
    fun providesAddWorkoutUseCase(workoutRepository: WorkoutRepository): AddWorkoutUseCase {
        return AddWorkoutUseCaseImpl(workoutRepository)
    }
}