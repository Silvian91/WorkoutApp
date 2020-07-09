package com.example.workoutapp.ui.common

import androidx.fragment.app.Fragment
import io.reactivex.disposables.CompositeDisposable

abstract class BaseFragment : Fragment() {

    val compositeDisposable = CompositeDisposable()

    override fun onDestroyView() {
        compositeDisposable.clear()

        super.onDestroyView()
    }

}