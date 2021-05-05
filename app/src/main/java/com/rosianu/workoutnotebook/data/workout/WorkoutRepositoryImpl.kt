package com.rosianu.workoutnotebook.data.workout

import com.rosianu.workoutnotebook.domain.workout.WorkoutRepository
import com.rosianu.workoutnotebook.domain.workout.model.WorkoutModel
import io.reactivex.Completable
import io.reactivex.Single

class WorkoutRepositoryImpl(private val workoutLocalDataSource: WorkoutLocalDataSource) :
    WorkoutRepository {

    override fun insertWorkout(workoutModel: WorkoutModel): Single<Long> {
        return workoutLocalDataSource.insertWorkout(workoutModel)
    }

    override fun getAllWorkouts(userId: Long): Single<List<WorkoutModel>> {
        return workoutLocalDataSource.getAllWorkouts(userId)
    }

    override fun getWorkoutTitle(workoutId: Long): Single<List<WorkoutModel>> {
        return workoutLocalDataSource.getWorkoutTitle(workoutId)
    }

    override fun deleteWorkout(workoutId: Long): Completable {
        return workoutLocalDataSource.deleteWorkout(workoutId)
    }

    override fun softDeleteWorkout(workoutId: Long): Completable {
        return workoutLocalDataSource.softDeleteWorkout(workoutId)
    }

    override fun undoSoftDeleteWorkout(workoutId: Long): Completable {
        return workoutLocalDataSource.undoSoftDeleteWorkout(workoutId)
    }

}