package com.example.workoutnotebook.ui.addroutine

import com.example.workoutnotebook.domain.addroutine.DeleteRoutineUseCase
import com.example.workoutnotebook.domain.addroutine.SaveRoutineUseCase
import com.example.workoutnotebook.domain.addroutine.SaveRoutineUseCase.Input
import com.example.workoutnotebook.domain.routine.model.RoutineModel
import com.example.workoutnotebook.domain.user.GetCurrentUserUseCase
import com.example.workoutnotebook.domain.user.GetCurrentUserUseCase.Output.*
import com.example.workoutnotebook.domain.user.model.UserModel
import com.example.workoutnotebook.ui.common.BaseTest
import io.mockk.every
import io.mockk.mockk
import io.reactivex.Single
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import com.example.workoutnotebook.domain.addroutine.SaveRoutineUseCase.Output.ErrorUnknown as SaveRoutineErrorUnknown
import com.example.workoutnotebook.domain.addroutine.SaveRoutineUseCase.Output.Success as SaveRoutineSuccess

class AddRoutineViewModelTest : BaseTest() {
    private lateinit var viewModel: AddRoutineViewModel
    private val saveRoutineUseCase: SaveRoutineUseCase = mockk()
    private val deleteRoutineUseCase: DeleteRoutineUseCase = mockk()
    private val getCurrentUserUseCase: GetCurrentUserUseCase = mockk()
    private val userModel = UserModel("username", "password", 1)
    private val routineModel = listOf(
        RoutineModel("Bench Press", "3", "4", "15.5", "2 minutes", 1, 1),
        RoutineModel("Squat", "3", "4", "17.5", "3 minutes", 1, 1)
    )

    @BeforeEach
    override fun setUp() {
        super.setUp()

        viewModel = AddRoutineViewModel(
            saveRoutineUseCase,
            deleteRoutineUseCase,
            getCurrentUserUseCase
        )
    }

    @Test
    fun `get current user emits expected output on success`() {
        every { getCurrentUserUseCase.execute(GetCurrentUserUseCase.Input) } returns
                Single.just(Success(userModel))

        viewModel.getUser()

        getCurrentUserUseCase.execute(GetCurrentUserUseCase.Input).test()
            .assertValue(Success(userModel))
    }

    @Test
    fun `get current user emits expected output error unauthorized`() {
        every { getCurrentUserUseCase.execute(GetCurrentUserUseCase.Input) } returns
                Single.just(ErrorUnauthorized)

        viewModel.getUser()

        getCurrentUserUseCase.execute(GetCurrentUserUseCase.Input).test()
            .assertValue(ErrorUnauthorized)
    }

    @Test
    fun `get current user emits expected output error unknown`() {
        every { getCurrentUserUseCase.execute(GetCurrentUserUseCase.Input) } returns
                Single.just(ErrorUnknown)

        viewModel.getUser()

        getCurrentUserUseCase.execute(GetCurrentUserUseCase.Input).test()
            .assertValue(ErrorUnknown)
    }

    @Test
    fun `on save routines emits expected output on success`(){
        every { saveRoutineUseCase.execute(Input(routineModel)) } returns
                Single.just(SaveRoutineSuccess)

        viewModel.saveRoutines(routineModel)

        saveRoutineUseCase.execute(Input(routineModel)).test()
            .assertValue(SaveRoutineSuccess)
    }

    @Test
    fun `on save routines emits expected output error unknown`(){
        every { saveRoutineUseCase.execute(Input(routineModel)) } returns
                Single.just(SaveRoutineErrorUnknown)

        viewModel.saveRoutines(routineModel)

        saveRoutineUseCase.execute(Input(routineModel)).test()
            .assertValue(SaveRoutineErrorUnknown)
    }

}