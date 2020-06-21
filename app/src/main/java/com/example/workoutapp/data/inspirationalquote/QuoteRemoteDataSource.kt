package com.example.workoutapp.data.inspirationalquote

import com.example.workoutapp.domain.inspirationalquote.model.QuoteModel
import io.reactivex.Single

interface QuoteRemoteDataSource {

    fun getRandomQuote(): Single<QuoteModel>
}