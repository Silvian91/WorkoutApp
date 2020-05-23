package com.example.workoutapp.di.showworkout

import android.content.Context
import android.content.SharedPreferences
import com.example.workoutapp.data.session.SessionKeyValueDataSource
import com.example.workoutapp.data.session.SessionKeyValueDataSourceImpl
import com.example.workoutapp.data.session.SessionManagerImpl
import com.example.workoutapp.data.user.UserLocalDataSource
import com.example.workoutapp.data.user.UserLocalDataSourceImpl
import com.example.workoutapp.data.user.UserRepositoryImpl
import com.example.workoutapp.data.workout.WorkoutLocalDataSource
import com.example.workoutapp.data.workout.WorkoutLocalDataSourceImpl
import com.example.workoutapp.data.workout.WorkoutRepositoryImpl
import com.example.workoutapp.domain.session.SessionManager
import com.example.workoutapp.domain.showroutine.DeleteWorkoutUseCase
import com.example.workoutapp.domain.showroutine.DeleteWorkoutUseCaseImpl
import com.example.workoutapp.domain.showworkout.GetWorkoutUseCase
import com.example.workoutapp.domain.showworkout.GetWorkoutUseCaseImpl
import com.example.workoutapp.domain.user.GetCurrentUserUseCase
import com.example.workoutapp.domain.user.GetCurrentUserUseCaseImpl
import com.example.workoutapp.domain.user.UserRepository
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
    fun providesWorkoutRepository(workoutLocalDataSource: WorkoutLocalDataSource): WorkoutRepository {
        return WorkoutRepositoryImpl(workoutLocalDataSource)
    }

    @Provides
    fun providesWorkoutLocalDataSource(context: Context): WorkoutLocalDataSource {
        return WorkoutLocalDataSourceImpl(context)
    }

    @Provides
    fun providesGetWorkoutsUseCase(workoutsRepository: WorkoutRepository): GetWorkoutUseCase {
        return GetWorkoutUseCaseImpl(workoutsRepository)
    }

    @Provides
    fun providesDeleteWorkoutFromRoutineUseCase(workoutRepository: WorkoutRepository): DeleteWorkoutUseCase {
        return DeleteWorkoutUseCaseImpl(workoutRepository)
    }

    @Provides
    fun providesGetCurrentUserUseCase(
        sessionManager: SessionManager,
        userRepository: UserRepository
    ): GetCurrentUserUseCase {
        return GetCurrentUserUseCaseImpl(sessionManager, userRepository)
    }

    @Provides
    fun providesUserRepository(userLocalDataSource: UserLocalDataSource): UserRepository {
        return UserRepositoryImpl(userLocalDataSource)
    }

    @Provides
    fun providesUserLocalDataSource(context: Context): UserLocalDataSource {
        return UserLocalDataSourceImpl(context)
    }

    @Provides
    fun providesSessionManager(sessionKeyValueDataSource: SessionKeyValueDataSource): SessionManager {
        return SessionManagerImpl(sessionKeyValueDataSource)
    }

    @Provides
    fun providesSessionKeyValueDataSource(sharedPreferences: SharedPreferences): SessionKeyValueDataSource {
        return SessionKeyValueDataSourceImpl(sharedPreferences)
    }

}