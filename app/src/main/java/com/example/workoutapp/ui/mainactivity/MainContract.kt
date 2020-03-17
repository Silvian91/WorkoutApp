package com.example.workoutapp.ui.mainactivity

import com.example.workoutapp.domain.chucknorrisquote.model.ChuckNorrisQuoteModel
import com.example.workoutapp.ui.common.BasePresenter
import com.example.workoutapp.ui.common.BaseView
import io.reactivex.Single

interface MainContract {

    interface View : BaseView<Presenter> {
        fun displayChuckNorrisQuote(quotes: ChuckNorrisQuoteModel)
    }

    interface Presenter : BasePresenter<View> {

        fun getChuckNorrisQuote(): Single<ChuckNorrisQuoteModel>

    }
}