package com.example.workoutapp.ui.main

import com.example.workoutapp.domain.chucknorrisquote.model.ChuckNorrisQuoteModel
import com.example.workoutapp.ui.common.BasePresenter
import com.example.workoutapp.ui.common.BaseView

interface HomeContract {

    interface View : BaseView<Presenter> {
        fun displayChuckNorrisQuote(quotes: ChuckNorrisQuoteModel)
    }

    interface Presenter : BasePresenter<View>
}