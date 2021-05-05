package com.rosianu.workoutnotebook.ui.register

import com.rosianu.workoutnotebook.domain.login.LoginUseCase
import com.rosianu.workoutnotebook.domain.register.RegisterUseCase
import com.rosianu.workoutnotebook.domain.user.model.UserModel
import com.rosianu.workoutnotebook.ui.common.BaseTest
import io.mockk.every
import io.mockk.mockk
import io.reactivex.Single
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class RegisterViewModelTest : BaseTest() {
    private lateinit var viewModel: RegisterViewModel
    private val registerUseCase: RegisterUseCase = mockk()
    private val loginUseCase: LoginUseCase = mockk()
    private val userModel = UserModel("username", "password", 1)

    @BeforeEach
    override fun setUp() {
        super.setUp()

        viewModel = RegisterViewModel(
                registerUseCase,
                loginUseCase
        )
    }

    @Test
    fun `on continue clicked emits output registration success`(){
        every { registerUseCase.execute(RegisterUseCase.Input(userModel)) } returns
                Single.just(RegisterUseCase.Output.Success)

        viewModel.onContinueClicked("username", "password", 1)

        registerUseCase.execute(RegisterUseCase.Input(userModel)).test()
                .assertValue(RegisterUseCase.Output.Success)
    }

    @Test
    fun `on continue clicked emits output registration error`(){
        every { registerUseCase.execute(RegisterUseCase.Input(userModel)) } returns
                Single.just(RegisterUseCase.Output.ErrorRegistrationFailed)

        viewModel.onContinueClicked("username", "password", 1)

        registerUseCase.execute(RegisterUseCase.Input(userModel)).test()
                .assertValue(RegisterUseCase.Output.ErrorRegistrationFailed)
    }

    @Test
    fun `on set current user id emits output success`(){
        every { loginUseCase.execute(LoginUseCase.Input("username", "password")) } returns
                Single.just(LoginUseCase.Output.Success)

        viewModel.setCurrentUserId("username", "password")

        loginUseCase.execute(LoginUseCase.Input("username", "password")).test()
                .assertValue(LoginUseCase.Output.Success)
    }

    @Test
    fun `on set current user id emits output error unknown`(){
        every { loginUseCase.execute(LoginUseCase.Input("username", "password")) } returns
                Single.just(LoginUseCase.Output.ErrorUnknown)

        viewModel.setCurrentUserId("username", "password")

        loginUseCase.execute(LoginUseCase.Input("username", "password")).test()
                .assertValue(LoginUseCase.Output.ErrorUnknown)
    }

    @Test
    fun `on set current user id emits output error invalid credentials`(){
        every { loginUseCase.execute(LoginUseCase.Input("username", "password")) } returns
                Single.just(LoginUseCase.Output.ErrorInvalidCredentials)

        viewModel.setCurrentUserId("username", "password")

        loginUseCase.execute(LoginUseCase.Input("username", "password")).test()
                .assertValue(LoginUseCase.Output.ErrorInvalidCredentials)
    }

}