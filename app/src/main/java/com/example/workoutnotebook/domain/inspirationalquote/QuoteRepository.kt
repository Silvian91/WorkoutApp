package com.example.workoutnotebook.domain.inspirationalquote

import com.example.workoutnotebook.domain.inspirationalquote.model.QuoteModel
import io.reactivex.Single

interface QuoteRepository {

    fun getRandomQuote(): Single<QuoteModel>
}