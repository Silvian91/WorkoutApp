package com.example.workoutapp.domain.inspirationalquote

import com.example.workoutapp.domain.inspirationalquote.model.QuoteModel
import io.reactivex.Single

interface QuoteRepository {

    fun getRandomQuote(): Single<QuoteModel>
}