package com.example.workoutnotebook.ui.login

import com.example.core.ui.error.ErrorType
import com.example.workoutnotebook.domain.login.LoginUseCase
import com.example.workoutnotebook.ui.common.BaseTest
import com.example.core.ui.error.ErrorType.Unknown
import com.example.core.ui.error.UIError
import io.mockk.every
import io.mockk.mockk
import io.reactivex.Single
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class LoginViewModelTest : BaseTest() {

    private lateinit var viewModel: LoginViewModel
    private val useCase : LoginUseCase = mockk()

    @BeforeEach
    override fun setUp(){
        super.setUp()

        viewModel = LoginViewModel(useCase)
    }

    @Test
    fun `on login clicked emits expected output on success`(){
        val expectedValue = LoginViewState(
            home = true,
            register = false,
            showError = false
        )

        every { useCase.execute(LoginUseCase.Input("user", "password")) } returns
                Single.just(LoginUseCase.Output.Success)

        viewModel.onLoginClicked("user", "password")

        viewModel.viewState.test().assertValue(expectedValue)
    }

    @Test
    fun `on login clicked emits expected output on invalid credentials`(){
        val expectedValue = LoginViewState(
            home = false,
            register = false,
            showError = true,
            errorType = ErrorType.ErrorInvalidCredentials
        )

        every { useCase.execute(LoginUseCase.Input("user", "password")) } returns
                Single.just(LoginUseCase.Output.ErrorInvalidCredentials)

        viewModel.onLoginClicked("user", "password")

        viewModel.viewState.test().assertValue(expectedValue)
    }

    @Test
    fun `on login clicked emits expected output on error unknown`(){
        val expectedValue = LoginViewState(
            home = false,
            register = false,
            showError = true,
            errorType = ErrorType.Unknown
        )

        every { useCase.execute(LoginUseCase.Input("user", "password")) } returns
                Single.just(LoginUseCase.Output.ErrorUnknown)

        viewModel.onLoginClicked("user", "password")

        viewModel.viewState.test().assertValue(expectedValue)
    }

    @Test
    fun `on signup clicked `(){
        val expectedValue = LoginViewState(
            home = false,
            register = true,
            showError = false
        )
        viewModel.onSignUpClicked()

        viewModel.viewState.test().assertValue(expectedValue)
    }

}