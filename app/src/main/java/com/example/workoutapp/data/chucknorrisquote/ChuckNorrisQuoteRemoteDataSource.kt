package com.example.workoutapp.data.chucknorrisquote

import com.example.workoutapp.domain.chucknorrisquote.model.ChuckNorrisQuoteModel
import io.reactivex.Single

interface ChuckNorrisQuoteRemoteDataSource {

    fun getRandomQuote(): Single<ChuckNorrisQuoteModel>
}