package com.example.workoutapp.ui.main

import com.example.workoutapp.ui.common.BaseViewModel
import io.reactivex.subjects.BehaviorSubject
import javax.inject.Inject

class MainViewModel @Inject constructor(

) : BaseViewModel() {

    val fabClicked = BehaviorSubject.create<Boolean>()

    fun onFloatingClicked() = fabClicked.onNext(true)

}