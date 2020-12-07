package com.example.workoutnotebook.data.inspirationalquote

import com.example.workoutnotebook.domain.inspirationalquote.model.QuoteModel
import io.reactivex.Single

interface QuoteRemoteDataSource {

    fun getRandomQuote(): Single<QuoteModel>
}