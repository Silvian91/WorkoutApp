package com.example.workoutapp.ui.mainactivity

import com.example.workoutapp.domain.chucknorrisquote.ChuckNorrisQuoteRepository
import com.example.workoutapp.domain.chucknorrisquote.model.ChuckNorrisQuoteModel
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers

class MainPresenter(
    private val chuckNorrisQuoteRepository: ChuckNorrisQuoteRepository,
    private val compositeDisposable: CompositeDisposable
) : MainContract.Presenter {

    private lateinit var view: MainContract.View

    override fun setView(view: MainContract.View) {
        this.view = view
    }

    override fun start() {
        chuckNorrisQuoteRepository.getRandomQuote()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { quotes -> view.displayChuckNorrisQuote(quotes)  }
            .addTo(compositeDisposable)
    }

    override fun finish() {
        compositeDisposable.clear()
    }

    override fun getChuckNorrisQuote(): Single<ChuckNorrisQuoteModel> {
        return chuckNorrisQuoteRepository.getRandomQuote()
    }

}