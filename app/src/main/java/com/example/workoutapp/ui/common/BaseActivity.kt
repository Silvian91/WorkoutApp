package com.example.workoutapp.ui.common

import androidx.lifecycle.ViewModelProvider
import dagger.Lazy
import dagger.android.support.DaggerAppCompatActivity
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

abstract class BaseActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: Lazy<ViewModelProvider.Factory>

    var compositeDisposable = CompositeDisposable()

    override fun onDestroy() {
        compositeDisposable.clear()

        super.onDestroy()
    }

}