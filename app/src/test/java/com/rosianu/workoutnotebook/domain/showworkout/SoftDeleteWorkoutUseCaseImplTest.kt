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

internal class SoftDeleteWorkoutUseCaseImplTest {

    private val repository: WorkoutRepository = mockk()
    private lateinit var useCase: SoftDeleteWorkoutUseCase
    private var workoutId: Long = 1

    @BeforeEach
    fun setUp() {
        useCase = SoftDeleteWorkoutUseCaseImpl(repository)
        RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }
    }

    @AfterEach
    fun tearDown() {
        RxAndroidPlugins.reset()
        RxJavaPlugins.reset()
    }

    @Test
    fun `verify on successful execution success output gets returned`() {
        every { repository.softDeleteWorkout(workoutId) } returns Completable.complete()

        useCase.execute(SoftDeleteWorkoutUseCase.Input(workoutId)).test()
            .assertValue(SoftDeleteWorkoutUseCase.Output.Success)
    }

    @Test
    fun `verify exceptions from source get mapped to unknown error`() {
        every { repository.softDeleteWorkout(workoutId) } returns Completable.error(RuntimeException())

        useCase.execute(SoftDeleteWorkoutUseCase.Input(workoutId)).test()
            .assertValue(SoftDeleteWorkoutUseCase.Output.ErrorUnknown)
    }

}