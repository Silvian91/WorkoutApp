package com.rosianu.workoutnotebook.http.inspirationalquote

import io.reactivex.Single
import retrofit2.http.GET

interface InspirationalApiService {

    @GET("randomQuotes")
    fun getRandomQuote(): Single<InspirationalQuoteResponse>

}