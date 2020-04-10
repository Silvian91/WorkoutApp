package com.example.workoutapp.ui.main

import com.example.workoutapp.domain.chucknorrisquote.ChuckNorrisQuoteRepository
import com.example.workoutapp.domain.extension.doOnIoObserveOnMain
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo

class MainPresenter(
    private val compositeDisposable: CompositeDisposable,
    private val chuckNorrisQuoteRepository: ChuckNorrisQuoteRepository
) : MainContract.Presenter {

    private lateinit var view: MainContract.View

    override fun setView(view: MainContract.View) {
        this.view = view
    }

    override fun start() {
        chuckNorrisQuoteRepository.getRandomQuote()
            .doOnIoObserveOnMain()
            .subscribe { quotes -> view.displayChuckNorrisQuote(quotes)  }
            .addTo(compositeDisposable)
    }

    override fun finish() {
        compositeDisposable.clear()
    }

}