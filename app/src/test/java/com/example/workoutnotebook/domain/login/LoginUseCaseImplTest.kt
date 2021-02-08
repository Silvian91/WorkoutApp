package com.example.workoutnotebook.domain.login

import com.example.workoutnotebook.domain.session.SessionManager
import com.example.workoutnotebook.domain.user.UserRepository
import com.example.workoutnotebook.domain.user.model.UserModel
import io.mockk.every
import io.mockk.mockk
import io.reactivex.Maybe
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class LoginUseCaseImplTest {

    private val repository: UserRepository = mockk()
    private val sessionManager: SessionManager = mockk()
    private lateinit var useCase: LoginUseCase
    private val model = UserModel("username", "password",1)
    private val username = "username"
    private val password = "password"
    private val invalidUsername = "u"
    private val invalidPassword = "p"
    private val emptyModel = UserModel("", "", null)

    @BeforeEach
    fun setUp() {
        useCase = LoginUseCaseImpl(repository, sessionManager)
        RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }
    }

    @AfterEach
    fun tearDown() {
        RxAndroidPlugins.reset()
        RxJavaPlugins.reset()
    }

    //TODO: FIX FAILING TEST
    @Test
    fun `verify on successful execution success output gets returned`() {
        every { repository.getUserCount(username) } returns Maybe.just(model)

        useCase.execute(LoginUseCase.Input(username, password)).test()
            .assertValue(LoginUseCase.Output.Success)
    }

    @Test
    fun `verify on invalid username invalid credentials error gets returned`() {
        every { repository.getUserCount(invalidUsername) } returns Maybe.just(emptyModel)

        useCase.execute(LoginUseCase.Input(invalidUsername, model.password)).test()
            .assertValue(LoginUseCase.Output.ErrorInvalidCredentials)
    }

    @Test
    fun `verify on invalid password invalid credentials error gets returned`() {
        every { repository.getUserCount(username) } returns Maybe.just(model)

        useCase.execute(LoginUseCase.Input(username, invalidPassword)).test()
            .assertValue(LoginUseCase.Output.ErrorInvalidCredentials)
    }

    @Test
    fun `verify exceptions from source get mapped to unknown error`() {
        every { repository.getUserCount(username) } returns Maybe.error(RuntimeException())

        useCase.execute(LoginUseCase.Input(username, model.password)).test()
            .assertValue(LoginUseCase.Output.ErrorUnknown)
    }

}