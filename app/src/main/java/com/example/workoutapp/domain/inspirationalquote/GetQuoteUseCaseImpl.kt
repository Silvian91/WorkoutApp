package com.example.workoutapp.domain.inspirationalquote

import com.example.workoutapp.domain.inspirationalquote.GetQuoteUseCase.Input
import com.example.workoutapp.domain.inspirationalquote.GetQuoteUseCase.Output
import com.example.workoutapp.domain.inspirationalquote.GetQuoteUseCase.Output.NetworkError
import com.example.workoutapp.domain.inspirationalquote.GetQuoteUseCase.Output.Success
import io.reactivex.Single

class GetQuoteUseCaseImpl(
    private val inspirationalQuoteRepository: InspirationalQuoteRepository
) : GetQuoteUseCase {
    override fun execute(input: Input): Single<Output> {
        return inspirationalQuoteRepository.getRandomQuote()
            .map { Success(it) as Output
            }
            .onErrorReturn { NetworkError }
    }

}