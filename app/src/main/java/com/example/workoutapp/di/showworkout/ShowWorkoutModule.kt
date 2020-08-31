package com.example.workoutapp.di.showworkout

import com.example.workoutapp.domain.showworkout.*
import com.example.workoutapp.domain.workout.WorkoutRepository
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