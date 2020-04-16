package com.example.workoutapp.di.showroutine

import android.content.Context
import com.example.workoutapp.data.routine.RoutineLocalDataSource
import com.example.workoutapp.data.routine.RoutineLocalDataSourceImpl
import com.example.workoutapp.data.routine.RoutineRepositoryImpl
import com.example.workoutapp.data.workout.WorkoutLocalDataSource
import com.example.workoutapp.data.workout.WorkoutLocalDataSourceImpl
import com.example.workoutapp.data.workout.WorkoutRepositoryImpl
import com.example.workoutapp.domain.routine.RoutineRepository
import com.example.workoutapp.domain.showroutine.DeleteWorkoutUseCase
import com.example.workoutapp.domain.showroutine.DeleteWorkoutUseCaseImpl
import com.example.workoutapp.domain.showroutine.GetRoutineUseCase
import com.example.workoutapp.domain.showroutine.GetRoutineUseCaseImpl
import com.example.workoutapp.domain.workout.WorkoutRepository
import com.example.workoutapp.ui.showroutine.ShowRoutineContract
import com.example.workoutapp.ui.showroutine.ShowRoutinePresenter
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable

@Module
class ShowRoutineModule {
    @Provides
    fun providesShowRoutinePresenter(
        deleteWorkoutUseCase: DeleteWorkoutUseCase,
        getRoutineUseCase: GetRoutineUseCase,
        compositeDisposable: CompositeDisposable
    ): ShowRoutineContract.Presenter {
        return ShowRoutinePresenter(deleteWorkoutUseCase, getRoutineUseCase, compositeDisposable)
    }

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
    fun providesDeleteWorkoutUseCase(workoutRepository: WorkoutRepository): DeleteWorkoutUseCase {
        return DeleteWorkoutUseCaseImpl(workoutRepository)
    }

    @Provides
    fun providesGetRoutineUseCase(routineRepository: RoutineRepository): GetRoutineUseCase {
        return GetRoutineUseCaseImpl(routineRepository)
    }

}