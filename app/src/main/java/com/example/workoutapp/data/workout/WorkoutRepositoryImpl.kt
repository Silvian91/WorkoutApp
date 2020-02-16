package com.example.workoutapp.data.workout

import io.reactivex.Completable
import io.reactivex.Single

class WorkoutRepositoryImpl(private val workoutLocalDataSource: WorkoutLocalDataSource): WorkoutRepository {

    override fun insertWorkout(workoutTitleField: WorkoutEntity): Single<Long> {
        return workoutLocalDataSource.insertWorkout(workoutTitleField)
    }

    override fun getAllWorkouts(): List<WorkoutEntity> {
        return workoutLocalDataSource.getAllWorkouts()
    }

    override fun deleteRoutine(workoutId: Long): Completable {
        return workoutLocalDataSource.deleteRoutines(workoutId)
    }

}