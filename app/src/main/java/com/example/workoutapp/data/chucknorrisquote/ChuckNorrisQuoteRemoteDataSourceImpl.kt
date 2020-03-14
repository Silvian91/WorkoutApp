package com.example.workoutapp.data.chucknorrisquote

import com.example.workoutapp.domain.chucknorrisquote.model.ChuckNorrisQuoteModel
import com.example.workoutapp.http.chucknorris.ChuckNorrisApiService
import io.reactivex.Single

class ChuckNorrisQuoteRemoteDataSourceImpl(private val chuckNorrisApiService: ChuckNorrisApiService) :
    ChuckNorrisQuoteRemoteDataSource {

    override fun getRandomQuote(): Single<ChuckNorrisQuoteModel> {
        return chuckNorrisApiService.getRandomQuote().map {
            it.toModel()
        }
    }

}