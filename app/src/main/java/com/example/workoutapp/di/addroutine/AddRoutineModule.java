package com.example.workoutapp.di.addroutine;

import android.content.Context;

import com.example.workoutapp.addroutine.AddRoutineContract;
import com.example.workoutapp.addroutine.AddRoutinePresenter;
import com.example.workoutapp.model.routine.RoutineLocalDataSource;
import com.example.workoutapp.model.routine.RoutineLocalDataSourceImpl;
import com.example.workoutapp.model.routine.RoutineRepository;
import com.example.workoutapp.model.routine.RoutineRepositoryImpl;
import com.example.workoutapp.model.workout.WorkoutLocalDataSource;
import com.example.workoutapp.model.workout.WorkoutLocalDataSourceImpl;
import com.example.workoutapp.model.workout.WorkoutRepository;
import com.example.workoutapp.model.workout.WorkoutRepositoryImpl;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;

@Module
public class AddRoutineModule {
    @Provides
    AddRoutineContract.Presenter providesAddRoutinePresenter(RoutineRepository routineRepository, WorkoutRepository workoutRepository, CompositeDisposable compositeDisposable) {
        return new AddRoutinePresenter(routineRepository, workoutRepository, compositeDisposable);
    }

    @Provides
    RoutineRepository providesRoutineRepository(RoutineLocalDataSource routineLocalDataSource) {
        return new RoutineRepositoryImpl(routineLocalDataSource);
    }

    @Provides
    RoutineLocalDataSource providesRoutineLocalDataSource(Context context) {
        return new RoutineLocalDataSourceImpl(context);
    }

    @Provides
    WorkoutRepository providesWorkoutRepository(WorkoutLocalDataSource workoutLocalDataSource) {
        return new WorkoutRepositoryImpl(workoutLocalDataSource);
    }

    @Provides
    WorkoutLocalDataSource providesWorkoutLocalDataSource(Context context) {
        return new WorkoutLocalDataSourceImpl(context);
    }

}
