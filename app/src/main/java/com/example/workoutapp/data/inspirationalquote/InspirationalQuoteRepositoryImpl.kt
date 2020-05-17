package com.example.workoutapp.data.inspirationalquote

import com.example.workoutapp.domain.inspirationalquote.InspirationalQuoteRepository
import com.example.workoutapp.domain.inspirationalquote.model.InspirationalQuoteModel
import io.reactivex.Single

class InspirationalQuoteRepositoryImpl(private val inspirationalQuoteRemoteDataSource: InspirationalQuoteRemoteDataSource) :
    InspirationalQuoteRepository {

    override fun getRandomQuote(): Single<InspirationalQuoteModel> {
        return inspirationalQuoteRemoteDataSource.getRandomQuote()
    }


}