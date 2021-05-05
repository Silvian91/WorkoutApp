package com.rosianu.workoutnotebook.domain.addroutine

import com.rosianu.workoutnotebook.domain.routine.RoutineRepository
import com.rosianu.workoutnotebook.domain.routine.model.RoutineModel
import io.mockk.every
import io.mockk.mockk
import io.reactivex.Completable
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class SaveRoutineUseCaseImplTest {

    private val repository: RoutineRepository = mockk()
    private lateinit var useCase: SaveRoutineUseCase
    private val model = listOf(
        RoutineModel("Bench Press", "3", "4", "15.5", "2 minutes", 1, 1),
        RoutineModel("Squat", "3", "4", "17.5", "3 minutes", 1, 1)
    )

    @BeforeEach
    fun setUp() {
        useCase = SaveRoutineUseCaseImpl(repository)
        RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }
    }

    @AfterEach
    fun tearDown() {
        RxAndroidPlugins.reset()
        RxJavaPlugins.reset()
    }

    @Test
    fun `verify on successful execution success output gets returned`() {
        every { repository.insertRoutine(model) } returns Completable.complete()

        useCase.execute(SaveRoutineUseCase.Input(model)).test().assertValue(SaveRoutineUseCase.Output.Success)
    }

    @Test
    fun `verify exceptions from source get mapped to unknown error`() {
        every { repository.insertRoutine(model) } returns Completable.error(RuntimeException())

        useCase.execute(SaveRoutineUseCase.Input(model)).test().assertValue(SaveRoutineUseCase.Output.ErrorUnknown)
    }

}