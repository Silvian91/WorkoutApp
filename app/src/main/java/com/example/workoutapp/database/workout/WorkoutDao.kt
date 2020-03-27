package com.example.workoutapp.database.workout

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface WorkoutDao {

    @Insert
    fun insertWorkout(workoutEntity: WorkoutEntity) : Long

    @get:Query("SELECT * FROM workout")
    val getAllWorkouts: List<WorkoutEntity>

    @Query("DELETE FROM workout WHERE id = :currentWorkoutId")
    fun deleteWorkoutRoutine(currentWorkoutId: Long)
}