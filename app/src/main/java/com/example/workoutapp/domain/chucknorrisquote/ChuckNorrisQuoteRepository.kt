package com.example.workoutapp.domain.chucknorrisquote

import com.example.workoutapp.domain.chucknorrisquote.model.ChuckNorrisQuoteModel
import io.reactivex.Single

interface ChuckNorrisQuoteRepository {

    fun getRandomQuote(): Single<ChuckNorrisQuoteModel>
}