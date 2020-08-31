package com.example.workoutapp.di.showroutine

import com.example.workoutapp.domain.routine.RoutineRepository
import com.example.workoutapp.domain.showroutine.DeleteWorkoutUseCase
import com.example.workoutapp.domain.showroutine.DeleteWorkoutUseCaseImpl
import com.example.workoutapp.domain.showroutine.GetRoutineUseCase
import com.example.workoutapp.domain.showroutine.GetRoutineUseCaseImpl
import com.example.workoutapp.domain.workout.WorkoutRepository
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