package com.example.workoutapp.di.showworkout

import com.example.workoutapp.domain.showroutine.DeleteWorkoutUseCase
import com.example.workoutapp.domain.showroutine.DeleteWorkoutUseCaseImpl
import com.example.workoutapp.domain.showworkout.GetWorkoutUseCase
import com.example.workoutapp.domain.showworkout.GetWorkoutUseCaseImpl
import com.example.workoutapp.domain.user.GetCurrentUserUseCase
import com.example.workoutapp.domain.workout.WorkoutRepository
import com.example.workoutapp.ui.showworkout.ShowWorkoutContract
import com.example.workoutapp.ui.showworkout.ShowWorkoutPresenter
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable

@Module
class ShowWorkoutModule {
    @Provides
    fun providesShowWorkoutPresenter(
        deleteWorkoutUseCase: DeleteWorkoutUseCase,
        workoutUseCase: GetWorkoutUseCase,
        getCurrentUserUseCase: GetCurrentUserUseCase,
        compositeDisposable: CompositeDisposable
    ): ShowWorkoutContract.Presenter {
        return ShowWorkoutPresenter(
            deleteWorkoutUseCase,
            workoutUseCase,
            getCurrentUserUseCase,
            compositeDisposable
        )
    }

    @Provides
    fun providesGetWorkoutsUseCase(workoutsRepository: WorkoutRepository): GetWorkoutUseCase {
        return GetWorkoutUseCaseImpl(workoutsRepository)
    }

}