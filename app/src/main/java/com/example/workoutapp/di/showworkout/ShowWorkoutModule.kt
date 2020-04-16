package com.example.workoutapp.di.showworkout

import android.content.Context
import com.example.workoutapp.data.workout.WorkoutLocalDataSource
import com.example.workoutapp.data.workout.WorkoutLocalDataSourceImpl
import com.example.workoutapp.data.workout.WorkoutRepositoryImpl
import com.example.workoutapp.domain.showworkout.GetWorkoutUseCase
import com.example.workoutapp.domain.showworkout.GetWorkoutUseCaseImpl
import com.example.workoutapp.domain.workout.WorkoutRepository
import com.example.workoutapp.ui.showworkout.ShowWorkoutContract
import com.example.workoutapp.ui.showworkout.ShowWorkoutPresenter
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable

@Module
class ShowWorkoutModule {
    @Provides
    fun providesShowWorkoutPresenter(
        workoutUseCase: GetWorkoutUseCase,
        compositeDisposable: CompositeDisposable
    ): ShowWorkoutContract.Presenter {
        return ShowWorkoutPresenter(workoutUseCase, compositeDisposable)
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
    fun providesGetWorkoutsUseCase(workoutsRepository: WorkoutRepository): GetWorkoutUseCase{
        return GetWorkoutUseCaseImpl(workoutsRepository)
    }
}