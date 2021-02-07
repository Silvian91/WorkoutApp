package com.example.workoutnotebook.domain.showroutine

import com.example.workoutnotebook.domain.workout.WorkoutRepository
import io.mockk.every
import io.mockk.mockk
import io.reactivex.Completable
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class DeleteWorkoutUseCaseImplTest {

    private val repository: WorkoutRepository = mockk()
    private lateinit var useCase: DeleteWorkoutUseCase
    private var workoutId: Long = 1

    @BeforeEach
    fun setUp() {
        useCase = DeleteWorkoutUseCaseImpl(repository)
        RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }
    }

    @AfterEach
    fun tearDown() {
        RxAndroidPlugins.reset()
        RxJavaPlugins.reset()
    }

    @Test
    fun `verify on successful execution success output gets returned`() {
        every { repository.deleteWorkout(workoutId) } returns Completable.complete()

        useCase.execute(DeleteWorkoutUseCase.Input(workoutId)).test()
            .assertValue(DeleteWorkoutUseCase.Output.Success)
    }

    @Test
    fun `verify exceptions from source get mapped to unknown error`() {
        every { repository.deleteWorkout(workoutId) } returns Completable.error(RuntimeException())

        useCase.execute(DeleteWorkoutUseCase.Input(workoutId)).test()
            .assertValue(DeleteWorkoutUseCase.Output.ErrorNotDeleted)
    }

}