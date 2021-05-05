package com.rosianu.workoutnotebook.domain.register

import com.rosianu.workoutnotebook.domain.user.UserRepository
import com.rosianu.workoutnotebook.domain.user.model.UserModel
import io.mockk.every
import io.mockk.mockk
import io.reactivex.Completable
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class RegisterUseCaseImplTest {

    private val repository: UserRepository = mockk()
    private lateinit var useCase: RegisterUseCase
    private val model = UserModel("username", "password", 1)

    @BeforeEach
    fun setUp() {
        useCase = RegisterUseCaseImpl(repository)
        RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }
    }

    @AfterEach
    fun tearDown() {
        RxAndroidPlugins.reset()
        RxJavaPlugins.reset()
    }

    @Test
    fun `verify on successful execution success output gets returned`() {
        every { repository.insertUser(model) } returns Completable.complete()

        useCase.execute(RegisterUseCase.Input(model)).test()
            .assertValue(RegisterUseCase.Output.Success)
    }

    @Test
    fun `verify exceptions from source get mapped to registration failed error`() {
        every { repository.insertUser(model) } returns Completable.error(RuntimeException())

        useCase.execute(RegisterUseCase.Input(model)).test()
            .assertValue(RegisterUseCase.Output.ErrorRegistrationFailed)
    }

}