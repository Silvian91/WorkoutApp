package com.example.workoutapp.di.addroutine

import android.content.Context
import com.example.workoutapp.ui.addroutine.AddRoutineContract
import com.example.workoutapp.ui.addroutine.AddRoutinePresenter
import com.example.workoutapp.data.routine.RoutineLocalDataSource
import com.example.workoutapp.data.routine.RoutineLocalDataSourceImpl
import com.example.workoutapp.data.database.routine.RoutineRepository
import com.example.workoutapp.data.routine.RoutineRepositoryImpl
import com.example.workoutapp.data.workout.WorkoutLocalDataSource
import com.example.workoutapp.data.workout.WorkoutLocalDataSourceImpl
import com.example.workoutapp.data.database.workout.WorkoutRepository
import com.example.workoutapp.data.workout.WorkoutRepositoryImpl
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable

@Module
class AddRoutineModule {
    @Provides
    fun providesAddRoutinePresenter(
        routineRepository: RoutineRepository,
        workoutRepository: WorkoutRepository,
        compositeDisposable: CompositeDisposable
    ): AddRoutineContract.Presenter {
        return AddRoutinePresenter(routineRepository, workoutRepository, compositeDisposable)
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