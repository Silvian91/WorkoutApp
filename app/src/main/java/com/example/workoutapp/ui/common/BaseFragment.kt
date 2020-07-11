package com.example.workoutapp.ui.common

import dagger.android.support.DaggerFragment
import io.reactivex.disposables.CompositeDisposable

abstract class BaseFragment : DaggerFragment() {

    val compositeDisposable = CompositeDisposable()

    override fun onDestroyView() {
        compositeDisposable.clear()

        super.onDestroyView()
    }

}