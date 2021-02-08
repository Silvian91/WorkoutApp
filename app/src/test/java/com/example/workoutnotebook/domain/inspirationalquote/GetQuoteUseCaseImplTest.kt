package com.example.workoutnotebook.domain.inspirationalquote

import com.example.workoutnotebook.domain.inspirationalquote.model.QuoteModel
import io.mockk.every
import io.mockk.mockk
import io.reactivex.Single
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class GetQuoteUseCaseImplTest {

    private val repository: QuoteRepository = mockk()
    private lateinit var useCase: GetQuoteUseCase
    private val model = QuoteModel("quote", "author")

    @BeforeEach
    fun setUp() {
        useCase = GetQuoteUseCaseImpl(repository)
        RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }
    }

    @AfterEach
    fun tearDown() {
        RxAndroidPlugins.reset()
        RxJavaPlugins.reset()
    }

    @Test
    fun `verify on successful execution success output gets returned`() {
        every { repository.getRandomQuote() } returns Single.just(model)

        useCase.execute(GetQuoteUseCase.Input).test()
            .assertValue(GetQuoteUseCase.Output.Success(model))
    }

    @Test
    fun `verify exceptions from source get mapped to unknown error`() {
        every { repository.getRandomQuote() } returns Single.error(RuntimeException())

        useCase.execute(GetQuoteUseCase.Input).test()
            .assertValue(GetQuoteUseCase.Output.NetworkError)
    }

}