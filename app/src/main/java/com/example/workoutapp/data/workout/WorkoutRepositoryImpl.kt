package com.example.workoutapp.data.workout

import com.example.workoutapp.domain.workout.WorkoutRepository
import com.example.workoutapp.domain.workout.model.WorkoutModel
import io.reactivex.Completable
import io.reactivex.Single

class WorkoutRepositoryImpl(private val workoutLocalDataSource: WorkoutLocalDataSource) :
    WorkoutRepository {

    override fun insertWorkout(workoutModel: WorkoutModel): Single<Long> {
        return workoutLocalDataSource.insertWorkout(workoutModel)
    }

    override fun getAllWorkouts(): Single<List<WorkoutModel>> {
        return workoutLocalDataSource.getAllWorkouts()
    }

    override fun deleteWorkout(workoutId: Long): Completable {
        return workoutLocalDataSource.deleteWorkout(workoutId)
    }

}