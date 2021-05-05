package com.rosianu.workoutnotebook.ui.showworkout

import com.rosianu.workoutnotebook.domain.showworkout.GetWorkoutUseCase
import com.rosianu.workoutnotebook.domain.showworkout.SoftDeleteWorkoutUseCase
import com.rosianu.workoutnotebook.domain.showworkout.UndoSoftDeleteWorkoutUseCase
import com.rosianu.workoutnotebook.domain.user.GetCurrentUserUseCase
import com.rosianu.workoutnotebook.domain.user.model.UserModel
import com.rosianu.workoutnotebook.domain.workout.model.WorkoutModel
import com.rosianu.workoutnotebook.ui.common.BaseTest
import io.mockk.every
import io.mockk.mockk
import io.reactivex.Single
import org.junit.jupiter.api.BeforeEach

import org.junit.jupiter.api.Test

class ShowWorkoutViewModelTest: BaseTest() {
    private lateinit var viewModel: ShowWorkoutViewModel
    private val softDeleteWorkoutUseCase: SoftDeleteWorkoutUseCase = mockk()
    private val undoSoftDeleteWorkoutUseCase: UndoSoftDeleteWorkoutUseCase = mockk()
    private val getWorkoutUseCase: GetWorkoutUseCase = mockk()
    private val getCurrentUserUseCase: GetCurrentUserUseCase = mockk()
    private val userModel = UserModel("username", "password", 1)
    private val workoutModel = listOf(
            WorkoutModel(1, "Jan 01 - Upper Body", 1)
    )

    @BeforeEach
    override fun setUp() {
        super.setUp()

        viewModel = ShowWorkoutViewModel(
            softDeleteWorkoutUseCase,
            undoSoftDeleteWorkoutUseCase,
            getWorkoutUseCase,
            getCurrentUserUseCase
        )
    }

    @Test
    fun `get user emits output success`(){
        every { getCurrentUserUseCase.execute(GetCurrentUserUseCase.Input) } returns
                Single.just(GetCurrentUserUseCase.Output.Success(userModel))

        viewModel.getUser()

        getCurrentUserUseCase.execute(GetCurrentUserUseCase.Input).test()
                .assertValue(GetCurrentUserUseCase.Output.Success(userModel))
    }

    @Test
    fun `get user emits output error unknown`(){
        every { getCurrentUserUseCase.execute(GetCurrentUserUseCase.Input) } returns
                Single.just(GetCurrentUserUseCase.Output.ErrorUnknown)

        viewModel.getUser()

        getCurrentUserUseCase.execute(GetCurrentUserUseCase.Input).test()
                .assertValue(GetCurrentUserUseCase.Output.ErrorUnknown)
    }

    @Test
    fun `get user emits output error unauthorized`(){
        every { getCurrentUserUseCase.execute(GetCurrentUserUseCase.Input) } returns
                Single.just(GetCurrentUserUseCase.Output.ErrorUnauthorized)

        viewModel.getUser()

        getCurrentUserUseCase.execute(GetCurrentUserUseCase.Input).test()
                .assertValue(GetCurrentUserUseCase.Output.ErrorUnauthorized)
    }

    @Test
    fun `get workouts emits output success`(){
        every { getWorkoutUseCase.execute(GetWorkoutUseCase.Input(1)) } returns
                Single.just(GetWorkoutUseCase.Output.Success(workoutModel))

        viewModel.getWorkoutsForUser(1)

        getWorkoutUseCase.execute(GetWorkoutUseCase.Input(1)).test()
                .assertValue(GetWorkoutUseCase.Output.Success(workoutModel))
    }

    @Test
    fun `get workouts emits output success no data`(){
        every { getWorkoutUseCase.execute(GetWorkoutUseCase.Input(1)) } returns
                Single.just(GetWorkoutUseCase.Output.SuccessNoData)

        viewModel.getWorkoutsForUser(1)

        getWorkoutUseCase.execute(GetWorkoutUseCase.Input(1)).test()
                .assertValue(GetWorkoutUseCase.Output.SuccessNoData)
    }

    @Test
    fun `get workouts emits output error unknown`(){
        every { getWorkoutUseCase.execute(GetWorkoutUseCase.Input(1)) } returns
                Single.just(GetWorkoutUseCase.Output.ErrorUnknown)

        viewModel.getWorkoutsForUser(1)

        getWorkoutUseCase.execute(GetWorkoutUseCase.Input(1)).test()
                .assertValue(GetWorkoutUseCase.Output.ErrorUnknown)
    }

    @Test
    fun `soft delete workout emits output success`(){
        every { softDeleteWorkoutUseCase.execute(SoftDeleteWorkoutUseCase.Input(1)) } returns
                Single.just(SoftDeleteWorkoutUseCase.Output.Success)

        viewModel.softDeleteWorkout(1)

        softDeleteWorkoutUseCase.execute(SoftDeleteWorkoutUseCase.Input(1)).test()
                .assertValue(SoftDeleteWorkoutUseCase.Output.Success)
    }

    @Test
    fun `soft delete workout emits output error unknown`(){
        every { softDeleteWorkoutUseCase.execute(SoftDeleteWorkoutUseCase.Input(1)) } returns
                Single.just(SoftDeleteWorkoutUseCase.Output.ErrorUnknown)

        viewModel.softDeleteWorkout(1)

        softDeleteWorkoutUseCase.execute(SoftDeleteWorkoutUseCase.Input(1)).test()
                .assertValue(SoftDeleteWorkoutUseCase.Output.ErrorUnknown)
    }

    @Test
    fun `undo soft delete workout emits output success`(){
        every { undoSoftDeleteWorkoutUseCase.execute(UndoSoftDeleteWorkoutUseCase.Input(1)) } returns
                Single.just(UndoSoftDeleteWorkoutUseCase.Output.Success)

        viewModel.onUndoDeletion(1)

        undoSoftDeleteWorkoutUseCase.execute(UndoSoftDeleteWorkoutUseCase.Input(1)).test()
                .assertValue(UndoSoftDeleteWorkoutUseCase.Output.Success)
    }

    @Test
    fun `undo soft delete workout emits output error unknown`(){
        every { undoSoftDeleteWorkoutUseCase.execute(UndoSoftDeleteWorkoutUseCase.Input(1)) } returns
                Single.just(UndoSoftDeleteWorkoutUseCase.Output.ErrorUnknown)

        viewModel.onUndoDeletion(1)

        undoSoftDeleteWorkoutUseCase.execute(UndoSoftDeleteWorkoutUseCase.Input(1)).test()
                .assertValue(UndoSoftDeleteWorkoutUseCase.Output.ErrorUnknown)
    }

}