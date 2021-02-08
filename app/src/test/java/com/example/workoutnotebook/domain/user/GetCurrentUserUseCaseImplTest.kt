package com.example.workoutnotebook.domain.user

import com.example.workoutnotebook.domain.session.SessionManager
import com.example.workoutnotebook.domain.session.UnauthorizedException
import com.example.workoutnotebook.domain.user.model.UserModel
import io.mockk.every
import io.mockk.mockk
import io.reactivex.Maybe
import io.reactivex.Single
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class GetCurrentUserUseCaseImplTest {

    private val sessionManager: SessionManager = mockk()
    private val repository: UserRepository = mockk()
    private lateinit var useCase: GetCurrentUserUseCase
    private val userId: Long = 3
    private val model = UserModel("username", "password", 3)


    @BeforeEach
    fun setUp() {
        useCase = GetCurrentUserUseCaseImpl(sessionManager, repository)
        RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }
    }

    @AfterEach
    fun tearDown() {
        RxAndroidPlugins.reset()
        RxJavaPlugins.reset()
    }

    @Test
    fun `verify on successful execution success output gets returned`() {
        every { sessionManager.getCurrentUserId() } returns Single.just(userId)
        every { repository.getUserCount(userId) } returns Single.just(model)

        useCase.execute(GetCurrentUserUseCase.Input).test()
            .assertValue(GetCurrentUserUseCase.Output.Success(model))
    }

    @Test
    fun `verify exceptions from source get mapped to unknown error`() {
        every { sessionManager.getCurrentUserId() } returns Single.error(RuntimeException())
        every { repository.getUserCount() } returns Maybe.error(RuntimeException())

        useCase.execute(GetCurrentUserUseCase.Input).test()
            .assertValue(GetCurrentUserUseCase.Output.ErrorUnknown)
    }

    @Test
    fun `verify unauthorized exception from source get mapped to error unauthorized`() {
        every { sessionManager.getCurrentUserId() } returns Single.error(UnauthorizedException())
        every { repository.getUserCount() } returns Maybe.error(UnauthorizedException())

        useCase.execute(GetCurrentUserUseCase.Input).test()
            .assertValue(GetCurrentUserUseCase.Output.ErrorUnauthorized)
    }
    
}