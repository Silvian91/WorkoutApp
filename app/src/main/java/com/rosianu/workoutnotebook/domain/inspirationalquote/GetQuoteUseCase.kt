package com.rosianu.workoutnotebook.domain.inspirationalquote

import com.rosianu.workoutnotebook.domain.common.BaseSingleUseCase
import com.rosianu.workoutnotebook.domain.common.BaseUseCase
import com.rosianu.workoutnotebook.domain.inspirationalquote.GetQuoteUseCase.Input
import com.rosianu.workoutnotebook.domain.inspirationalquote.GetQuoteUseCase.Output
import com.rosianu.workoutnotebook.domain.inspirationalquote.model.QuoteModel

interface GetQuoteUseCase : BaseSingleUseCase<Input, Output> {

    object Input : BaseUseCase.Input

    sealed class Output : BaseUseCase.Output {

        data class Success(val quote: QuoteModel) : Output()
        object NetworkError : Output()
    }
}