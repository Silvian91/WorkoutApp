package com.example.workoutapp.domain.inspirationalquote

import com.example.workoutapp.domain.common.BaseSingleUseCase
import com.example.workoutapp.domain.common.BaseUseCase
import com.example.workoutapp.domain.inspirationalquote.GetQuoteUseCase.Input
import com.example.workoutapp.domain.inspirationalquote.GetQuoteUseCase.Output
import com.example.workoutapp.domain.inspirationalquote.model.QuoteModel

interface GetQuoteUseCase : BaseSingleUseCase<Input, Output> {

    object Input : BaseUseCase.Input

    sealed class Output : BaseUseCase.Output {

        data class Success(val quote: QuoteModel) : Output()
        object NetworkError : Output()
    }
}