package com.example.workoutapp.domain.inspirationalquote

import com.example.workoutapp.domain.inspirationalquote.model.InspirationalQuoteModel
import io.reactivex.Single

interface InspirationalQuoteRepository {

    fun getRandomQuote(): Single<InspirationalQuoteModel>
}