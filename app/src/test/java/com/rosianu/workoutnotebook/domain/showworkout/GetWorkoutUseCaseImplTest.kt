package com.rosianu.workoutnotebook.domain.showworkout

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

internal class GetWorkoutUseCaseImplTest {

    private val repository: WorkoutRepository = mockk()
    private lateinit var useCase: GetWorkoutUseCase
    private var userId: Long = 3
    private var model = listOf(
        WorkoutModel(1, "Jan 03 - Upper Body", 3),
        WorkoutModel(2, "Jan 05 - Lower Body", 3)
    )
    private var modelEmpty = listOf<WorkoutModel>()

    @BeforeEach
    fun setUp() {
        useCase = GetWorkoutUseCaseImpl(repository)
        RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }
    }

    @AfterEach
    fun tearDown() {
        RxAndroidPlugins.reset()
        RxJavaPlugins.reset()
    }

    @Test
    fun `verify on successful execution success output gets returned`() {
        every { repository.getAllWorkouts(userId) } returns Single.just(model)

        useCase.execute(GetWorkoutUseCase.Input(userId)).test()
            .assertValue(GetWorkoutUseCase.Output.Success(model))
    }

    @Test
    fun `verify when no workouts exits success no data gets returned`() {
        every { repository.getAllWorkouts(1) } returns Single.just(modelEmpty)

        useCase.execute(GetWorkoutUseCase.Input(1)).test()
            .assertValue(GetWorkoutUseCase.Output.SuccessNoData)
    }

    @Test
    fun `verify exceptions from source get mapped to unknown error`() {
        every { repository.getAllWorkouts(userId) } returns Single.error(RuntimeException())

        useCase.execute(GetWorkoutUseCase.Input(userId)).test()
            .assertValue(GetWorkoutUseCase.Output.ErrorUnknown)
    }

}