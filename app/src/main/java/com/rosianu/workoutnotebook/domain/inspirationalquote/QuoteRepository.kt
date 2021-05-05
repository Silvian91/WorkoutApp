package com.rosianu.workoutnotebook.domain.inspirationalquote

import com.rosianu.workoutnotebook.domain.inspirationalquote.model.QuoteModel
import io.reactivex.Single

interface QuoteRepository {

    fun getRandomQuote(): Single<QuoteModel>
}