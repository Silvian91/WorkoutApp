package com.rosianu.workoutnotebook.domain.inspirationalquote

import com.rosianu.workoutnotebook.domain.inspirationalquote.GetQuoteUseCase.Input
import com.rosianu.workoutnotebook.domain.inspirationalquote.GetQuoteUseCase.Output
import com.rosianu.workoutnotebook.domain.inspirationalquote.GetQuoteUseCase.Output.NetworkError
import com.rosianu.workoutnotebook.domain.inspirationalquote.GetQuoteUseCase.Output.Success
import io.reactivex.Single

class GetQuoteUseCaseImpl(
    private val quoteRepository: QuoteRepository
) : GetQuoteUseCase {
    override fun execute(input: Input): Single<Output> {
        return quoteRepository.getRandomQuote()
            .map { Success(it) as Output }
            .onErrorReturn { NetworkError }
    }
}