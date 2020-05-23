package com.example.workoutapp.database.workout

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface WorkoutDao {

    @Insert
    fun insertWorkout(workoutEntity: WorkoutEntity): Long

    @Query("SELECT * FROM workout WHERE userId = :currentUserId")
    fun getAllWorkouts(currentUserId: Long): List<WorkoutEntity>

    @Query("DELETE FROM workout WHERE id = :currentWorkoutId")
    fun deleteWorkoutFromRoutine(currentWorkoutId: Long)

}