package com.example.workoutnotebook.di.showroutine

import com.example.workoutnotebook.domain.routine.RoutineRepository
import com.example.workoutnotebook.domain.showroutine.*
import com.example.workoutnotebook.domain.workout.WorkoutRepository
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