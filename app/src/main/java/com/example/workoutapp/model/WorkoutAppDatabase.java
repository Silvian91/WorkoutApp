package com.example.workoutapp.model;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.workoutapp.model.routine.RoutineDao;
import com.example.workoutapp.model.routine.RoutineEntity;
import com.example.workoutapp.model.workout.WorkoutDao;
import com.example.workoutapp.model.workout.WorkoutEntity;

@Database(entities = {RoutineEntity.class, WorkoutEntity.class}, exportSchema = false, version = 1)
public abstract class WorkoutAppDatabase extends RoomDatabase {
    public final static String DB_NAME = "workout_db";
    public static WorkoutAppDatabase instance;

    public static synchronized WorkoutAppDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), WorkoutAppDatabase.class, DB_NAME)
                        .fallbackToDestructiveMigration()
                        .build();
        }
        return instance;
    }

    public abstract RoutineDao routineDao();

    public abstract WorkoutDao workoutDao();
}
