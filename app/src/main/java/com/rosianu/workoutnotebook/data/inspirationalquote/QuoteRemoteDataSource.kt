package com.rosianu.workoutnotebook.data.inspirationalquote

import com.rosianu.workoutnotebook.domain.inspirationalquote.model.QuoteModel
import io.reactivex.Single

interface QuoteRemoteDataSource {

    fun getRandomQuote(): Single<QuoteModel>
}