package com.example.workoutapp.data.chucknorrisquote

import com.example.workoutapp.domain.chucknorrisquote.ChuckNorrisQuoteRepository
import com.example.workoutapp.domain.chucknorrisquote.model.ChuckNorrisQuoteModel
import io.reactivex.Single

class ChuckNorrisQuoteRepositoryImpl(private val chuckNorrisQuoteRemoteDataSource: ChuckNorrisQuoteRemoteDataSource): ChuckNorrisQuoteRepository {

    override fun getRandomQuote(): Single<ChuckNorrisQuoteModel> {
        return chuckNorrisQuoteRemoteDataSource.getRandomQuote()
    }


}