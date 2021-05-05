package com.rosianu.workoutnotebook.data.inspirationalquote

import com.rosianu.workoutnotebook.domain.inspirationalquote.QuoteRepository
import com.rosianu.workoutnotebook.domain.inspirationalquote.model.QuoteModel
import io.reactivex.Single

class QuoteRepositoryImpl(private val quoteRemoteDataSource: QuoteRemoteDataSource) :
    QuoteRepository {

    override fun getRandomQuote(): Single<QuoteModel> {
        return quoteRemoteDataSource.getRandomQuote()
    }


}