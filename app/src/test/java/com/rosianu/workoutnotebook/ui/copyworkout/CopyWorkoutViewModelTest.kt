package com.rosianu.workoutnotebook.ui.copyworkout

import com.rosianu.workoutnotebook.domain.showworkout.GetWorkoutUseCase
import com.rosianu.workoutnotebook.domain.user.GetCurrentUserUseCase
import com.rosianu.workoutnotebook.domain.user.model.UserModel
import com.rosianu.workoutnotebook.domain.workout.model.WorkoutModel
import com.rosianu.workoutnotebook.ui.common.BaseTest
import io.mockk.every
import io.mockk.mockk
import io.reactivex.Single
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class CopyWorkoutViewModelTest : BaseTest() {
    private lateinit var viewModel: CopyWorkoutViewModel
    private val getWorkoutUseCase: GetWorkoutUseCase = mockk()
    private val getCurrentUserUseCase: GetCurrentUserUseCase = mockk()
    private val userModel = UserModel("username", "password", 1)
    private val workoutModel = listOf(
            WorkoutModel(1, "Jan 03 - Upper Body", 3)
    )

    @BeforeEach
    override fun setUp() {
        super.setUp()

        viewModel = CopyWorkoutViewModel(
                getWorkoutUseCase,
                getCurrentUserUseCase
        )
    }

    @Test
    fun `get current user emits expected output on success`() {
        every { getCurrentUserUseCase.execute(GetCurrentUserUseCase.Input) } returns
                Single.just(GetCurrentUserUseCase.Output.Success(userModel))

        viewModel.getUser()

        getCurrentUserUseCase.execute(GetCurrentUserUseCase.Input).test()
                .assertValue(GetCurrentUserUseCase.Output.Success(userModel))
    }

    @Test
    fun `get current user emits expected output error unauthorized`() {
        every { getCurrentUserUseCase.execute(GetCurrentUserUseCase.Input) } returns
                Single.just(GetCurrentUserUseCase.Output.ErrorUnauthorized)

        viewModel.getUser()

        getCurrentUserUseCase.execute(GetCurrentUserUseCase.Input).test()
                .assertValue(GetCurrentUserUseCase.Output.ErrorUnauthorized)
    }

    @Test
    fun `get current user emits expected output error unknown`() {
        every { getCurrentUserUseCase.execute(GetCurrentUserUseCase.Input) } returns
                Single.just(GetCurrentUserUseCase.Output.ErrorUnknown)

        viewModel.getUser()

        getCurrentUserUseCase.execute(GetCurrentUserUseCase.Input).test()
                .assertValue(GetCurrentUserUseCase.Output.ErrorUnknown)
    }

    @Test
    fun `get workout for user emits expected output on success`(){
        every { getWorkoutUseCase.execute(GetWorkoutUseCase.Input(3)) } returns
                Single.just(GetWorkoutUseCase.Output.Success(workoutModel))

        viewModel.getWorkoutsForUser(3)

        getWorkoutUseCase.execute(GetWorkoutUseCase.Input(3)).test()
                .assertValue(GetWorkoutUseCase.Output.Success(workoutModel))
    }

    @Test
    fun `get workout for user emits expected output success no data`(){
        every { getWorkoutUseCase.execute(GetWorkoutUseCase.Input(2)) } returns
                Single.just(GetWorkoutUseCase.Output.SuccessNoData)

        viewModel.getWorkoutsForUser(2)

        getWorkoutUseCase.execute(GetWorkoutUseCase.Input(2)).test()
                .assertValue(GetWorkoutUseCase.Output.SuccessNoData)
    }

    @Test
    fun `get workout for user emits expected output error unknown`(){
        every { getWorkoutUseCase.execute(GetWorkoutUseCase.Input(1)) } returns
                Single.just(GetWorkoutUseCase.Output.ErrorUnknown)

        viewModel.getWorkoutsForUser(1)

        getWorkoutUseCase.execute(GetWorkoutUseCase.Input(1)).test()
                .assertValue(GetWorkoutUseCase.Output.ErrorUnknown)
    }

}