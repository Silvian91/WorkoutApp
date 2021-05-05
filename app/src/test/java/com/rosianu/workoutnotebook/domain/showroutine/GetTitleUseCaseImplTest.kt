package com.rosianu.workoutnotebook.domain.showroutine

import com.rosianu.workoutnotebook.domain.workout.WorkoutRepository
import com.rosianu.workoutnotebook.domain.workout.model.WorkoutModel
import io.mockk.every
import io.mockk.mockk
import io.reactivex.Single
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class GetTitleUseCaseImplTest {

    private val repository: WorkoutRepository = mockk()
    private lateinit var useCase: GetTitleUseCase
    private var workoutId: Long = 1
    private var model = listOf(
        WorkoutModel(1, "Jan 03 - Upper Body", 3),
        WorkoutModel(2, "Jan 05 - Lower Body", 3)
    )

    @BeforeEach
    fun setUp() {
        useCase = GetTitleUseCaseImpl(repository)
        RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }
    }

    @AfterEach
    fun tearDown() {
        RxAndroidPlugins.reset()
        RxJavaPlugins.reset()
    }

    @Test
    fun `verify on successful execution success output gets returned`() {
        every { repository.getWorkoutTitle(workoutId) } returns Single.just(model)

        useCase.execute(GetTitleUseCase.Input(workoutId)).test()
            .assertValue(GetTitleUseCase.Output.Success(model))
    }

    @Test
    fun `verify exceptions from source get mapped to error no title`() {
        every { repository.getWorkoutTitle(workoutId) } returns Single.error(RuntimeException())

        useCase.execute(GetTitleUseCase.Input(workoutId)).test()
            .assertValue(GetTitleUseCase.Output.ErrorNoTitle)
    }

}