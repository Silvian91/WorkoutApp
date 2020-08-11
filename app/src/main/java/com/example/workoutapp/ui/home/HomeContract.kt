package com.example.workoutapp.ui.home

import com.example.workoutapp.ui.common.BasePresenter
import com.example.workoutapp.ui.common.BaseView
import com.example.workoutapp.ui.home.adapter.HomeAdapter
import com.example.workoutapp.ui.home.adapter.HomeItemWrapper

interface HomeContract {

    interface View : BaseView<Presenter> {
        fun showNetworkError()
        fun handleShowWorkoutClick()
        fun showData(items: List<HomeItemWrapper>)
    }

    interface Presenter : BasePresenter<View>,
        HomeAdapter.ButtonHolderViewListener {
    }
}