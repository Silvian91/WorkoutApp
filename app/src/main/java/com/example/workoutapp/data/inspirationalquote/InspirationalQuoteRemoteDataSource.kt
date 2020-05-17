package com.example.workoutapp.data.inspirationalquote

import com.example.workoutapp.domain.inspirationalquote.model.InspirationalQuoteModel
import io.reactivex.Single

interface InspirationalQuoteRemoteDataSource {

    fun getRandomQuote(): Single<InspirationalQuoteModel>
}