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
    private var username = "username"
    private var password = "password"
    private var userId: Long = 1

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

    @Test
    fun `verify on successful execution success output gets returned`() {
        every { repository.getUserCount(username) } returns Maybe.just(UserModel(username, password, userId))

        useCase.execute(LoginUseCase.Input(username, password)).test()
            .assertValue(LoginUseCase.Output.Success)
    }

    @Test
    fun `verify exceptions from source get mapped to unknown error`() {
        every { repository.getUserCount(username) } returns Maybe.error(RuntimeException())

        useCase.execute(LoginUseCase.Input(username, password)).test()
            .assertValue(LoginUseCase.Output.ErrorUnknown)
    }

}