package com.example.workoutapp.ui.common

import androidx.appcompat.app.AppCompatActivity
import io.reactivex.disposables.CompositeDisposable

abstract class BaseActivity : AppCompatActivity() {

    var compositeDisposable = CompositeDisposable()

    override fun onDestroy() {
        compositeDisposable.clear()

        super.onDestroy()
    }

}