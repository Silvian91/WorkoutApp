package com.example.workoutapp.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.workoutapp.model.routine.RoutineDao
import com.example.workoutapp.model.routine.RoutineEntity
import com.example.workoutapp.model.workout.WorkoutDao
import com.example.workoutapp.model.workout.WorkoutEntity

@Database(
    entities = [RoutineEntity::class, WorkoutEntity::class],
    exportSchema = false,
    version = 1
)
abstract class WorkoutAppDatabase : RoomDatabase() {
    abstract fun routineDao(): RoutineDao
    abstract fun workoutDao(): WorkoutDao

    companion object {
        const val DB_NAME = "workout_db"
        var instance: WorkoutAppDatabase? = null
        @Synchronized
        fun getInstance(context: Context): WorkoutAppDatabase {
            if (instance == null) {
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    WorkoutAppDatabase::class.java,
                    DB_NAME
                )
                    .fallbackToDestructiveMigration()
                    .build()
            }
            return instance!!
        }
    }
}