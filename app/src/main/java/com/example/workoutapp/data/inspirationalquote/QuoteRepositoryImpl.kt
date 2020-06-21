package com.example.workoutapp.data.inspirationalquote

import com.example.workoutapp.domain.inspirationalquote.QuoteRepository
import com.example.workoutapp.domain.inspirationalquote.model.QuoteModel
import io.reactivex.Single

class QuoteRepositoryImpl(private val quoteRemoteDataSource: QuoteRemoteDataSource) :
    QuoteRepository {

    override fun getRandomQuote(): Single<QuoteModel> {
        return quoteRemoteDataSource.getRandomQuote()
    }


}