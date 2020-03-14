package com.example.workoutapp.ui.mainactivity

import com.example.workoutapp.domain.chucknorrisquote.ChuckNorrisQuoteRepository

class MainPresenter(
    private val chuckNorrisQuoteRepository: ChuckNorrisQuoteRepository
) : MainContract.Presenter {

    private lateinit var view: MainContract.View

    override fun setView(view: MainContract.View) {
        this.view = view
    }

    override fun start() {}

    override fun finish() {}

    override fun getChuckNorrisQuote() {
        chuckNorrisQuoteRepository.getRandomQuote()
    }

}