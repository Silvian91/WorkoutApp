package com.rosianu.workoutnotebook.domain.addworkout

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

internal class AddWorkoutUseCaseImplTest {

    private val repository: WorkoutRepository = mockk()
    private lateinit var useCase: AddWorkoutUseCase
    private val model = WorkoutModel(1, "Jan 03 - Upper Body", 3)
    private val workoutId: Long = 1

    @BeforeEach
    fun setUp() {
        useCase = AddWorkoutUseCaseImpl(repository)
        RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }
    }

    @AfterEach
    fun tearDown() {
        RxAndroidPlugins.reset()
        RxJavaPlugins.reset()
    }

    @Test
    fun `verify on successful execution success output gets returned`() {
        every { repository.insertWorkout(model) } returns Single.just(workoutId)

        useCase.execute(AddWorkoutUseCase.Input(model)).test()
            .assertValue(AddWorkoutUseCase.Output.Success(workoutId))
    }

    @Test
    fun `verify exceptions from source get mapped to unknown error`() {
        every { repository.insertWorkout(model) } returns Single.error(RuntimeException())

        useCase.execute(AddWorkoutUseCase.Input(model)).test()
            .assertValue(AddWorkoutUseCase.Output.ErrorUnknown)
    }

}