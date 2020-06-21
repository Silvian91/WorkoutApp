package com.example.workoutapp.data.inspirationalquote

import com.example.workoutapp.domain.inspirationalquote.model.QuoteModel
import com.example.workoutapp.http.inspirationalquote.InspirationalApiService
import io.reactivex.Single

class QuoteRemoteDataSourceImpl(private val inspirationalApiService: InspirationalApiService) :
    QuoteRemoteDataSource {

    override fun getRandomQuote(): Single<QuoteModel> {
        return inspirationalApiService.getRandomQuote().map {
            it.toModel()
        }
    }

}