package com.example.workoutapp.di.addroutine

import android.content.Context
import com.example.workoutapp.addroutine.AddRoutineContract
import com.example.workoutapp.addroutine.AddRoutinePresenter
import com.example.workoutapp.model.routine.RoutineLocalDataSource
import com.example.workoutapp.model.routine.RoutineLocalDataSourceImpl
import com.example.workoutapp.model.routine.RoutineRepository
import com.example.workoutapp.model.routine.RoutineRepositoryImpl
import com.example.workoutapp.model.workout.WorkoutLocalDataSource
import com.example.workoutapp.model.workout.WorkoutLocalDataSourceImpl
import com.example.workoutapp.model.workout.WorkoutRepository
import com.example.workoutapp.model.workout.WorkoutRepositoryImpl
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable

@Module
class AddRoutineModule {
    @Provides
    fun providesAddRoutinePresenter(
        routineRepository: RoutineRepository?,
        workoutRepository: WorkoutRepository?,
        compositeDisposable: CompositeDisposable?
    ): AddRoutineContract.Presenter {
        return AddRoutinePresenter(routineRepository!!, workoutRepository!!, compositeDisposable!!)
    }

    @Provides
    fun providesRoutineRepository(routineLocalDataSource: RoutineLocalDataSource?): RoutineRepository {
        return RoutineRepositoryImpl(routineLocalDataSource!!)
    }

    @Provides
    fun providesRoutineLocalDataSource(context: Context?): RoutineLocalDataSource {
        return RoutineLocalDataSourceImpl(context!!)
    }

    @Provides
    fun providesWorkoutRepository(workoutLocalDataSource: WorkoutLocalDataSource?): WorkoutRepository {
        return WorkoutRepositoryImpl(workoutLocalDataSource!!)
    }

    @Provides
    fun providesWorkoutLocalDataSource(context: Context?): WorkoutLocalDataSource {
        return WorkoutLocalDataSourceImpl(context!!)
    }
}