package com.example.workoutnotebook.data.inspirationalquote

import com.example.workoutnotebook.domain.inspirationalquote.model.QuoteModel
import com.example.workoutnotebook.http.inspirationalquote.InspirationalApiService
import io.reactivex.Single

class QuoteRemoteDataSourceImpl(private val inspirationalApiService: InspirationalApiService) :
    QuoteRemoteDataSource {

    override fun getRandomQuote(): Single<QuoteModel> {
        return inspirationalApiService.getRandomQuote().map {
            it.toModel()
        }
    }

}