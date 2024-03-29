package com.rosianu.workoutnotebook.domain.profile

import com.rosianu.workoutnotebook.domain.routine.RoutineRepository
import com.rosianu.workoutnotebook.domain.routine.model.RoutineModel
import io.mockk.every
import io.mockk.mockk
import io.reactivex.Single
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class GetUserRoutinesUseCaseImplTest {

    private val repository: RoutineRepository = mockk()
    private lateinit var useCase: GetUserRoutinesUseCase
    private val model = listOf(
        RoutineModel("Bench Press", "3", "4", "15.5", "2 minutes", 1, 1),
        RoutineModel("Squat", "3", "4", "17.5", "3 minutes", 1, 1)
    )
    private val emptyModel = listOf<RoutineModel>()

    @BeforeEach
    fun setUp() {
        useCase = GetUserRoutinesUseCaseImpl(repository)
        RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }
    }

    @AfterEach
    fun tearDown() {
        RxAndroidPlugins.reset()
        RxJavaPlugins.reset()
    }

    @Test
    fun `verify on successful execution success output gets returned`() {
        every { repository.getUserRoutine(1) } returns Single.just(model)

        useCase.execute(GetUserRoutinesUseCase.Input(1)).test()
            .assertValue(GetUserRoutinesUseCase.Output.Success(model))
    }

    @Test
    fun `verify on no routines success no routines gets returned`() {
        every { repository.getUserRoutine(2) } returns Single.just(emptyModel)

        useCase.execute(GetUserRoutinesUseCase.Input(2)).test()
            .assertValue(GetUserRoutinesUseCase.Output.SuccessNoRoutines)
    }

    @Test
    fun `verify exceptions from source get mapped to unknown error`() {
        every { repository.getUserRoutine(1) } returns Single.error(RuntimeException())

        useCase.execute(GetUserRoutinesUseCase.Input(1)).test()
            .assertValue(GetUserRoutinesUseCase.Output.ErrorUnknown)
    }

}