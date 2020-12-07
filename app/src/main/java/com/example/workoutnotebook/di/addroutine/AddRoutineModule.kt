package com.example.workoutnotebook.di.addroutine

import android.content.Context
import com.example.workoutnotebook.data.routine.RoutineLocalDataSource
import com.example.workoutnotebook.data.routine.RoutineLocalDataSourceImpl
import com.example.workoutnotebook.data.routine.RoutineRepositoryImpl
import com.example.workoutnotebook.data.workout.WorkoutLocalDataSource
import com.example.workoutnotebook.data.workout.WorkoutLocalDataSourceImpl
import com.example.workoutnotebook.data.workout.WorkoutRepositoryImpl
import com.example.workoutnotebook.domain.addroutine.DeleteRoutineUseCase
import com.example.workoutnotebook.domain.addroutine.DeleteRoutineUseCaseImpl
import com.example.workoutnotebook.domain.addroutine.SaveRoutineUseCase
import com.example.workoutnotebook.domain.addroutine.SaveRoutineUseCaseImpl
import com.example.workoutnotebook.domain.routine.RoutineRepository
import com.example.workoutnotebook.domain.workout.WorkoutRepository
import dagger.Module
import dagger.Provides

@Module
class AddRoutineModule {

    @Provides
    fun providesRoutineRepository(routineLocalDataSource: RoutineLocalDataSource): RoutineRepository {
        return RoutineRepositoryImpl(routineLocalDataSource)
    }

    @Provides
    fun providesRoutineLocalDataSource(context: Context): RoutineLocalDataSource {
        return RoutineLocalDataSourceImpl(context)
    }

    @Provides
    fun providesWorkoutRepository(workoutLocalDataSource: WorkoutLocalDataSource): WorkoutRepository {
        return WorkoutRepositoryImpl(workoutLocalDataSource)
    }

    @Provides
    fun providesWorkoutLocalDataSource(context: Context): WorkoutLocalDataSource {
        return WorkoutLocalDataSourceImpl(context)
    }

    @Provides
    fun providesSaveRoutineUseCase(routineRepository: RoutineRepository): SaveRoutineUseCase {
        return SaveRoutineUseCaseImpl(routineRepository)
    }

    @Provides
    fun providesDeleteRoutineUseCase(workoutRepository: WorkoutRepository): DeleteRoutineUseCase {
        return DeleteRoutineUseCaseImpl(workoutRepository)
    }
}