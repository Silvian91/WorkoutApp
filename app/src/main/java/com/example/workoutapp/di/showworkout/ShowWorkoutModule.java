package com.example.workoutapp.di.showworkout;

import android.content.Context;

import com.example.workoutapp.addworkout.AddWorkoutPresenter;
import com.example.workoutapp.model.workout.WorkoutLocalDataSource;
import com.example.workoutapp.model.workout.WorkoutLocalDataSourceImpl;
import com.example.workoutapp.model.workout.WorkoutRepository;
import com.example.workoutapp.model.workout.WorkoutRepositoryImpl;
import com.example.workoutapp.showworkout.ShowWorkoutContract;
import com.example.workoutapp.showworkout.ShowWorkoutPresenter;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;

@Module
public class ShowWorkoutModule {
    @Provides
    ShowWorkoutContract.Presenter providesShowWorkoutPresenter(WorkoutRepository workoutRepository, CompositeDisposable compositeDisposable) {
        return new ShowWorkoutPresenter(workoutRepository, compositeDisposable);
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
