package com.example.workoutnotebook.domain.user

import io.mockk.every
import io.mockk.mockk
import io.reactivex.Maybe
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class IsUserDBEmptyUseCaseImplTest {

    private val repository: UserRepository = mockk()
    private lateinit var useCase: IsUserDBEmptyUseCase
    private val userId: Long = 1
    private val noUser: Long = 0

    @BeforeEach
    fun setUp() {
        useCase = IsUserDBEmptyUseCaseImpl(repository)
        RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }
    }

    @AfterEach
    fun tearDown() {
        RxAndroidPlugins.reset()
        RxJavaPlugins.reset()
    }

    @Test
    fun `verify on existing user DB not empty output gets returned`() {
        every { repository.getUserCount() } returns Maybe.just(userId)

        useCase.execute(IsUserDBEmptyUseCase.Input).test()
            .assertValue(IsUserDBEmptyUseCase.Output.DBNotEmpty)
    }

    @Test
    fun `verify on no user DB empty output gets returned`() {
        every { repository.getUserCount() } returns Maybe.just(noUser)

        useCase.execute(IsUserDBEmptyUseCase.Input).test()
            .assertValue(IsUserDBEmptyUseCase.Output.DBEmpty)
    }

    @Test
    fun `verify exceptions from source get mapped to unknown error`() {
        every { repository.getUserCount() } returns Maybe.error(RuntimeException())

        useCase.execute(IsUserDBEmptyUseCase.Input).test()
            .assertValue(IsUserDBEmptyUseCase.Output.ErrorUnknown)
    }

}