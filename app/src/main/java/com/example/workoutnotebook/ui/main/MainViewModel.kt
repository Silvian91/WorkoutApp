package com.example.workoutnotebook.ui.main

import com.example.core.ui.BaseViewModel
import io.reactivex.subjects.BehaviorSubject
import javax.inject.Inject

class MainViewModel @Inject constructor(

) : BaseViewModel() {

    val fabClicked = BehaviorSubject.create<Boolean>()

    fun onFloatingClicked() = fabClicked.onNext(true)

}