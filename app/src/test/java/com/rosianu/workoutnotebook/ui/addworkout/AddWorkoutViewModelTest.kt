package com.rosianu.workoutnotebook.ui.addworkout

import com.rosianu.workoutnotebook.domain.addworkout.AddWorkoutUseCase
import com.rosianu.workoutnotebook.domain.user.GetCurrentUserUseCase
import com.rosianu.workoutnotebook.domain.user.model.UserModel
import com.rosianu.workoutnotebook.domain.workout.model.WorkoutModel
import com.rosianu.workoutnotebook.ui.common.BaseTest
import io.mockk.every
import io.mockk.mockk
import io.reactivex.Single
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class AddWorkoutViewModelTest: BaseTest() {
    private lateinit var viewModel: AddWorkoutViewModel
    private val addWorkoutUseCase: AddWorkoutUseCase = mockk()
    private val getCurrentUserUseCase: GetCurrentUserUseCase = mockk()
    private val userModel = UserModel("username", "password", 1)
    private val workoutModel = WorkoutModel(1, "Jan 01 - Upper Body", 1)

    @BeforeEach
    override fun setUp() {
        super.setUp()

        viewModel = AddWorkoutViewModel(
            addWorkoutUseCase,
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
    fun `on save workout emits expected output on success`() {
        every { addWorkoutUseCase.execute(AddWorkoutUseCase.Input(workoutModel)) } returns
                Single.just(AddWorkoutUseCase.Output.Success(1))

        viewModel.saveWorkout(workoutModel)

        addWorkoutUseCase.execute(AddWorkoutUseCase.Input(workoutModel)).test()
            .assertValue(AddWorkoutUseCase.Output.Success(1))
    }

    @Test
    fun `on save workout emits expected output error unknown`() {
        every { addWorkoutUseCase.execute(AddWorkoutUseCase.Input(workoutModel)) } returns
                Single.just(AddWorkoutUseCase.Output.ErrorUnknown)

        viewModel.saveWorkout(workoutModel)

        addWorkoutUseCase.execute(AddWorkoutUseCase.Input(workoutModel)).test()
            .assertValue(AddWorkoutUseCase.Output.ErrorUnknown)
    }

}