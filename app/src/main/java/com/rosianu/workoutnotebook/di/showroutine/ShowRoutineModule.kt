package com.rosianu.workoutnotebook.di.showroutine

import com.rosianu.workoutnotebook.domain.routine.RoutineRepository
import com.rosianu.workoutnotebook.domain.showroutine.*
import com.rosianu.workoutnotebook.domain.workout.WorkoutRepository
import dagger.Module
import dagger.Provides

@Module
class ShowRoutineModule {

    @Provides
    fun providesDeleteWorkoutUseCase(workoutRepository: WorkoutRepository): DeleteWorkoutUseCase {
        return DeleteWorkoutUseCaseImpl(workoutRepository)
    }

    @Provides
    fun providesGetRoutineUseCase(routineRepository: RoutineRepository): GetRoutineUseCase {
        return GetRoutineUseCaseImpl(routineRepository)
    }

    @Provides
    fun providesGetTitleUseCase(workoutRepository: WorkoutRepository): GetTitleUseCase {
        return GetTitleUseCaseImpl(workoutRepository)
    }

}