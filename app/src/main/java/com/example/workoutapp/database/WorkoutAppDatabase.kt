package com.example.workoutapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.workoutapp.database.routine.RoutineDao
import com.example.workoutapp.database.routine.RoutineEntity
import com.example.workoutapp.database.user.UserDao
import com.example.workoutapp.database.user.UserEntity
import com.example.workoutapp.database.workout.WorkoutDao
import com.example.workoutapp.database.workout.WorkoutEntity

@Database(
    entities = [RoutineEntity::class, WorkoutEntity::class, UserEntity::class],
    exportSchema = false,
    version = 2
)
abstract class WorkoutAppDatabase : RoomDatabase() {
    abstract fun routineDao(): RoutineDao
    abstract fun workoutDao(): WorkoutDao
    abstract fun userDao(): UserDao

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