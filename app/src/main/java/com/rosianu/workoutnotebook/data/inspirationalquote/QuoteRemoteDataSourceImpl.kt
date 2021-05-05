package com.rosianu.workoutnotebook.data.inspirationalquote

import com.rosianu.workoutnotebook.domain.inspirationalquote.model.QuoteModel
import com.rosianu.workoutnotebook.http.inspirationalquote.InspirationalApiService
import io.reactivex.Single

class QuoteRemoteDataSourceImpl(private val inspirationalApiService: InspirationalApiService) :
    QuoteRemoteDataSource {

    override fun getRandomQuote(): Single<QuoteModel> {
        return inspirationalApiService.getRandomQuote().map {
            it.toModel()
        }
    }

}