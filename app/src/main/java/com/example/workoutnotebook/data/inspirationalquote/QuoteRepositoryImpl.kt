package com.example.workoutnotebook.data.inspirationalquote

import com.example.workoutnotebook.domain.inspirationalquote.QuoteRepository
import com.example.workoutnotebook.domain.inspirationalquote.model.QuoteModel
import io.reactivex.Single

class QuoteRepositoryImpl(private val quoteRemoteDataSource: QuoteRemoteDataSource) :
    QuoteRepository {

    override fun getRandomQuote(): Single<QuoteModel> {
        return quoteRemoteDataSource.getRandomQuote()
    }


}