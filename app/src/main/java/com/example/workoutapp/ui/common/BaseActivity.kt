package com.example.workoutapp.ui.common

import dagger.android.support.DaggerAppCompatActivity
import io.reactivex.disposables.CompositeDisposable

abstract class BaseActivity : DaggerAppCompatActivity() {

    var compositeDisposable = CompositeDisposable()

    override fun onDestroy() {
        compositeDisposable.clear()

        super.onDestroy()
    }

}