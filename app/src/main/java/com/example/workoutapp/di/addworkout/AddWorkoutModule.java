package com.example.workoutapp.di.addworkout;

import android.content.Context;

import com.example.workoutapp.addworkout.AddWorkoutContract;
import com.example.workoutapp.addworkout.AddWorkoutPresenter;
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
public class AddWorkoutModule {
    @Provides
    AddWorkoutContract.Presenter providesAddWorkoutPresenter(WorkoutRepository workoutRepository, CompositeDisposable compositeDisposable) {
        return new AddWorkoutPresenter(workoutRepository, compositeDisposable);
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
