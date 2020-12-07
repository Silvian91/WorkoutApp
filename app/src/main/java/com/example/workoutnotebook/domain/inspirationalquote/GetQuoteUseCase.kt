package com.example.workoutnotebook.domain.inspirationalquote

import com.example.workoutnotebook.domain.common.BaseSingleUseCase
import com.example.workoutnotebook.domain.common.BaseUseCase
import com.example.workoutnotebook.domain.inspirationalquote.GetQuoteUseCase.Input
import com.example.workoutnotebook.domain.inspirationalquote.GetQuoteUseCase.Output
import com.example.workoutnotebook.domain.inspirationalquote.model.QuoteModel

interface GetQuoteUseCase : BaseSingleUseCase<Input, Output> {

    object Input : BaseUseCase.Input

    sealed class Output : BaseUseCase.Output {

        data class Success(val quote: QuoteModel) : Output()
        object NetworkError : Output()
    }
}