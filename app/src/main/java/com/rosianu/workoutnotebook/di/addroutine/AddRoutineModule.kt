package com.rosianu.workoutnotebook.di.addroutine

import android.content.Context
import com.rosianu.workoutnotebook.data.routine.RoutineLocalDataSource
import com.rosianu.workoutnotebook.data.routine.RoutineLocalDataSourceImpl
import com.rosianu.workoutnotebook.data.routine.RoutineRepositoryImpl
import com.rosianu.workoutnotebook.data.workout.WorkoutLocalDataSource
import com.rosianu.workoutnotebook.data.workout.WorkoutLocalDataSourceImpl
import com.rosianu.workoutnotebook.data.workout.WorkoutRepositoryImpl
import com.rosianu.workoutnotebook.domain.addroutine.DeleteRoutineUseCase
import com.rosianu.workoutnotebook.domain.addroutine.DeleteRoutineUseCaseImpl
import com.rosianu.workoutnotebook.domain.addroutine.SaveRoutineUseCase
import com.rosianu.workoutnotebook.domain.addroutine.SaveRoutineUseCaseImpl
import com.rosianu.workoutnotebook.domain.routine.RoutineRepository
import com.rosianu.workoutnotebook.domain.workout.WorkoutRepository
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