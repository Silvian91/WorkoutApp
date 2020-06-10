package com.example.workoutapp.data.inspirationalquote

import com.example.workoutapp.domain.inspirationalquote.QuoteRepository
import com.example.workoutapp.domain.inspirationalquote.model.InspirationalQuoteModel
import io.reactivex.Single

class QuoteRepositoryImpl(private val inspirationalQuoteRemoteDataSource: InspirationalQuoteRemoteDataSource) :
    QuoteRepository {

    override fun getRandomQuote(): Single<InspirationalQuoteModel> {
        return inspirationalQuoteRemoteDataSource.getRandomQuote()
    }


}