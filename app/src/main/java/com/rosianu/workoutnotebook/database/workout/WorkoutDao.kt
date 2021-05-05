package com.rosianu.workoutnotebook.database.workout

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface WorkoutDao {

    @Insert
    fun insertWorkout(workoutEntity: WorkoutEntity): Long

    @Query("SELECT * FROM workout WHERE userId = :currentUserId AND isDeleted = 0")
    fun getAllWorkouts(currentUserId: Long): List<WorkoutEntity>

    @Query("SELECT * FROM workout WHERE id = :workoutId")
    fun getWorkoutTitle(workoutId: Long): List<WorkoutEntity>

    @Query("DELETE FROM workout WHERE id = :currentWorkoutId")
    fun deleteWorkoutFromRoutine(currentWorkoutId: Long)

    @Query("UPDATE workout SET isDeleted = 1 WHERE id = :currentWorkoutId")
    fun softDeleteWorkout(currentWorkoutId: Long)

    @Query("UPDATE workout SET isDeleted = 0 WHERE id = :currentWorkoutId")
    fun undoSoftDeleteWorkout(currentWorkoutId: Long)

}