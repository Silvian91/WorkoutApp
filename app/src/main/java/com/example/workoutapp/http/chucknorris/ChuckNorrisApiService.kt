package com.example.workoutapp.http.chucknorris

import io.reactivex.Single
import retrofit2.http.GET

interface ChuckNorrisApiService {

    @GET("jokes/random")
    fun getRandomQuote(): Single<ChuckNorrisQuoteResponse>

}