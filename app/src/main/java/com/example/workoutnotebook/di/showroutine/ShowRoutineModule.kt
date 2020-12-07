package com.example.workoutnotebook.di.showroutine

import com.example.workoutnotebook.domain.routine.RoutineRepository
import com.example.workoutnotebook.domain.showroutine.DeleteWorkoutUseCase
import com.example.workoutnotebook.domain.showroutine.DeleteWorkoutUseCaseImpl
import com.example.workoutnotebook.domain.showroutine.GetRoutineUseCase
import com.example.workoutnotebook.domain.showroutine.GetRoutineUseCaseImpl
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

}