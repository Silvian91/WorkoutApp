package com.example.core.ui

import androidx.lifecycle.ViewModelProvider
import dagger.Lazy
import dagger.android.support.DaggerFragment
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

abstract class BaseFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: Lazy<ViewModelProvider.Factory>

    val compositeDisposable = CompositeDisposable()

    override fun onDestroyView() {
        compositeDisposable.clear()

        super.onDestroyView()
    }

}