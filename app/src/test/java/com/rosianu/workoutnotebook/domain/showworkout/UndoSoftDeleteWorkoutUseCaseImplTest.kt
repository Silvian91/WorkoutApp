package com.rosianu.workoutnotebook.domain.showworkout

import com.rosianu.workoutnotebook.domain.workout.WorkoutRepository
import io.mockk.every
import io.mockk.mockk
import io.reactivex.Completable
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class UndoSoftDeleteWorkoutUseCaseImplTest {

    private val repository: WorkoutRepository = mockk()
    private lateinit var useCase: UndoSoftDeleteWorkoutUseCase
    private var workoutId: Long = 1

    @BeforeEach
    fun setUp() {
        useCase = UndoSoftDeleteWorkoutUseCaseImpl(repository)
        RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }
    }

    @AfterEach
    fun tearDown() {
        RxAndroidPlugins.reset()
        RxJavaPlugins.reset()
    }

    @Test
    fun `verify on successful execution success output gets returned`() {
        every { repository.undoSoftDeleteWorkout(workoutId) } returns Completable.complete()

        useCase.execute(UndoSoftDeleteWorkoutUseCase.Input(workoutId)).test()
            .assertValue(UndoSoftDeleteWorkoutUseCase.Output.Success)
    }

    @Test
    fun `verify exceptions from source get mapped to unknown error`() {
        every { repository.undoSoftDeleteWorkout(workoutId) } returns Completable.error(RuntimeException())

        useCase.execute(UndoSoftDeleteWorkoutUseCase.Input(workoutId)).test()
            .assertValue(UndoSoftDeleteWorkoutUseCase.Output.ErrorUnknown)
    }

}