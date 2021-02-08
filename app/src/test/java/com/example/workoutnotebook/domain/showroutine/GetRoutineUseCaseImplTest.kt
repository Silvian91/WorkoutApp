package com.example.workoutnotebook.domain.showroutine

import com.example.workoutnotebook.domain.routine.RoutineRepository
import com.example.workoutnotebook.domain.routine.model.RoutineModel
import io.mockk.every
import io.mockk.mockk
import io.reactivex.Single
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class GetRoutineUseCaseImplTest {

    private val repository: RoutineRepository = mockk()
    private lateinit var useCase: GetRoutineUseCase
    private val model = listOf(
        RoutineModel("Bench Press", "3", "4", "15.5", "2 minutes", 1, 1),
        RoutineModel("Squat", "3", "4", "17.5", "3 minutes", 1, 1)
    )
    private val workoutId: Long = 1
    private val modelEmpty = listOf<RoutineModel>()

    @BeforeEach
    fun setUp() {
        useCase = GetRoutineUseCaseImpl(repository)
        RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }
    }

    @AfterEach
    fun tearDown() {
        RxAndroidPlugins.reset()
        RxJavaPlugins.reset()
    }

    @Test
    fun `verify on successful execution success output gets returned`() {
        every { repository.getRoutine(workoutId) } returns Single.just(model)

        useCase.execute(GetRoutineUseCase.Input(workoutId)).test()
            .assertValue(GetRoutineUseCase.Output.Success(model))
    }

    @Test
    fun `verify when no routines exist error no routines gets returned`() {
        every { repository.getRoutine(11) } returns Single.just(modelEmpty)

        useCase.execute(GetRoutineUseCase.Input(11)).test()
            .assertValue(GetRoutineUseCase.Output.ErrorNoRoutines)
    }

}