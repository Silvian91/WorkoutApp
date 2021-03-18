package com.example.workoutnotebook.ui.login

import com.example.workoutnotebook.domain.login.LoginUseCase
import com.example.workoutnotebook.ui.common.BaseTest
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
        every { useCase.execute(LoginUseCase.Input("user", "password")) } returns Single.just(LoginUseCase.Output.Success)
        viewModel.onLoginClicked("user", "password")

        viewModel.viewState.test().assertValue(expectedValue)
    }
}