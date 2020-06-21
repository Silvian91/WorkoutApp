package com.example.workoutapp.ui.main

import com.example.workoutapp.ui.common.BasePresenter
import com.example.workoutapp.ui.common.BaseView
import com.example.workoutapp.ui.main.recyclerviewadapter.HomeAdapter
import com.example.workoutapp.ui.main.recyclerviewadapter.HomeItemsWrapper

interface HomeContract {

    interface View : BaseView<Presenter> {
        fun showNetworkError()
        fun handleAddWorkoutClick()
        fun handleShowWorkoutClick()
        fun showData(items: List<HomeItemsWrapper>)
    }

    interface Presenter : BasePresenter<View>,
        HomeAdapter.ButtonHolderViewListener {
    }
}