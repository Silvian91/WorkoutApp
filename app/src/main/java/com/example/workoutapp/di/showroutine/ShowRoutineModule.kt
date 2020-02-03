package com.example.workoutapp.di.showroutine

import android.content.Context
import com.example.workoutapp.model.routine.RoutineLocalDataSource
import com.example.workoutapp.model.routine.RoutineLocalDataSourceImpl
import com.example.workoutapp.model.routine.RoutineRepository
import com.example.workoutapp.model.routine.RoutineRepositoryImpl
import com.example.workoutapp.model.workout.WorkoutLocalDataSource
import com.example.workoutapp.model.workout.WorkoutLocalDataSourceImpl
import com.example.workoutapp.model.workout.WorkoutRepository
import com.example.workoutapp.model.workout.WorkoutRepositoryImpl
import com.example.workoutapp.showroutine.ShowRoutineContract
import com.example.workoutapp.showroutine.ShowRoutinePresenter
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable

@Module
class ShowRoutineModule {
    @Provides
    fun providesShowRoutinePresenter(
        routineRepository: RoutineRepository,
        workoutRepository: WorkoutRepository,
        compositeDisposable: CompositeDisposable
    ): ShowRoutineContract.Presenter {
        return ShowRoutinePresenter(routineRepository, workoutRepository, compositeDisposable)
    }

    @Provides
    fun providesRoutineRepository(routineLocalDataSource: RoutineLocalDataSource): RoutineRepository {
        return RoutineRepositoryImpl(routineLocalDataSource)
    }

    @Provides
    fun providesRoutineLocalDataSource(context: Context): RoutineLocalDataSource {
        return RoutineLocalDataSourceImpl(context)
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