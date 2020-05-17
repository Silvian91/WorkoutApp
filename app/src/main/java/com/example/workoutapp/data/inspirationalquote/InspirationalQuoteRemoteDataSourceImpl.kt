package com.example.workoutapp.data.inspirationalquote

import com.example.workoutapp.domain.inspirationalquote.model.InspirationalQuoteModel
import com.example.workoutapp.http.inspirationalquote.InspirationalApiService
import io.reactivex.Single

class InspirationalQuoteRemoteDataSourceImpl(private val inspirationalApiService: InspirationalApiService) :
    InspirationalQuoteRemoteDataSource {

    override fun getRandomQuote(): Single<InspirationalQuoteModel> {
        return inspirationalApiService.getRandomQuote().map {
            it.toModel()
        }
    }

}