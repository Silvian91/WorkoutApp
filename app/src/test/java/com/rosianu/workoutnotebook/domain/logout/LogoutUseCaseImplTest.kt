package com.rosianu.workoutnotebook.domain.logout

import com.rosianu.workoutnotebook.domain.session.SessionManager
import io.mockk.every
import io.mockk.mockk
import io.reactivex.Completable
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class LogoutUseCaseImplTest {

    private val sessionManager: SessionManager = mockk()
    private lateinit var useCase: LogoutUseCase

    @BeforeEach
    fun setUp() {
        useCase = LogoutUseCaseImpl(sessionManager)
        RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }
    }

    @AfterEach
    fun tearDown() {
        RxAndroidPlugins.reset()
        RxJavaPlugins.reset()
    }

    @Test
    fun `verify on successful execution success output gets returned`() {
        every { sessionManager.setCurrentUserId(null) } returns Completable.complete()

        useCase.execute(LogoutUseCase.Input).test()
            .assertValue(LogoutUseCase.Output.Success)
    }

    @Test
    fun `verify exceptions from source get mapped to unknown error`() {
        every { sessionManager.setCurrentUserId(null) } returns Completable.error(RuntimeException())

        useCase.execute(LogoutUseCase.Input).test()
            .assertValue(LogoutUseCase.Output.ErrorUnknown)
    }

}