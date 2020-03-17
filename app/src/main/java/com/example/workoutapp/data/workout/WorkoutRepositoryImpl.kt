package com.example.workoutapp.data.workout

import com.example.workoutapp.domain.workout.model.WorkoutModel
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single

class WorkoutRepositoryImpl(private val workoutLocalDataSource: WorkoutLocalDataSource): WorkoutRepository {

    override fun insertWorkout(workoutTitleField: WorkoutEntity): Single<Long> {
        return workoutLocalDataSource.insertWorkout(workoutTitleField)
    }

    override fun getAllWorkouts(): Observable<ArrayList<WorkoutModel>> {
        return workoutLocalDataSource.getAllWorkouts()
    }

    override fun deleteRoutine(workoutId: Long): Completable {
        return workoutLocalDataSource.deleteRoutines(workoutId)
    }

}