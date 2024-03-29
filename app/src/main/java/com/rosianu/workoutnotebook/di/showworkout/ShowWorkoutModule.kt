package com.rosianu.workoutnotebook.di.showworkout

import com.rosianu.workoutnotebook.domain.showworkout.*
import com.rosianu.workoutnotebook.domain.workout.WorkoutRepository
import dagger.Module
import dagger.Provides

@Module
class ShowWorkoutModule {

    @Provides
    fun providesGetWorkoutsUseCase(workoutsRepository: WorkoutRepository): GetWorkoutUseCase {
        return GetWorkoutUseCaseImpl(workoutsRepository)
    }

    @Provides
    fun providesSoftDeleteWorkoutUseCase(workoutsRepository: WorkoutRepository): SoftDeleteWorkoutUseCase {
        return SoftDeleteWorkoutUseCaseImpl(workoutsRepository)
    }

    @Provides
    fun providesUndoSoftDeleteWorkoutUseCase(workoutsRepository: WorkoutRepository): UndoSoftDeleteWorkoutUseCase {
        return UndoSoftDeleteWorkoutUseCaseImpl(workoutsRepository)
    }

}