package com.rosianu.workoutnotebook.database.routine

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface RoutineDao {

    @Insert
    fun insertRoutine(routineEntity: RoutineEntity)

    @Query("SELECT * FROM routine WHERE workoutId = :currentWorkoutId")
    fun getWorkoutRoutines(currentWorkoutId: Long): List<RoutineEntity>

    @Query("SELECT * FROM routine WHERE userId = :currentUserId")
    fun getAllUserRoutines(currentUserId: Long): List<RoutineEntity>

    @Query("DELETE FROM routine WHERE workoutId = :currentWorkoutId")
    fun deleteWorkoutRoutine(currentWorkoutId: Long)

}