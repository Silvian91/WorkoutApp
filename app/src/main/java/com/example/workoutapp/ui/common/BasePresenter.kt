package com.example.workoutapp.ui.common

interface BasePresenter<T : BaseView<*>> {

    fun setView(view: T)
    fun start()
    fun finish()
}