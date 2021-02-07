package com.example.workoutnotebook.domain.addroutine

import com.example.workoutnotebook.domain.addroutine.DeleteRoutineUseCase.Input
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

internal class DeleteRoutineUseCaseImplTest {

    private val repository: WorkoutRepository = mockk()
    private lateinit var useCase: DeleteRoutineUseCase
    private val workoutId: Long = 1

    @BeforeEach
    fun setUp() {
        useCase = DeleteRoutineUseCaseImpl(repository)
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

        useCase.execute(Input(workoutId)).test().assertValue(DeleteRoutineUseCase.Output.Success)
    }

    @Test
    fun `verify exceptions from source get mapped to unknown error`() {
        every { repository.deleteWorkout(workoutId) } returns Completable.error(RuntimeException())

        useCase.execute(Input(workoutId)).test().assertValue(DeleteRoutineUseCase.Output.ErrorUnknown)
    }

}