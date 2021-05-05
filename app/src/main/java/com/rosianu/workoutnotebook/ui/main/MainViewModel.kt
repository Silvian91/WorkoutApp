package com.rosianu.workoutnotebook.ui.main

import com.rosianu.core.ui.BaseViewModel
import io.reactivex.subjects.BehaviorSubject
import javax.inject.Inject

class MainViewModel @Inject constructor(

) : BaseViewModel() {

    val addWorkoutClicked = BehaviorSubject.create<Boolean>()
    val copyWorkoutClicked = BehaviorSubject.create<Boolean>()

    fun onAddWorkoutClicked() = addWorkoutClicked.onNext(true)
    fun onCopyWorkoutClicked() = copyWorkoutClicked.onNext(true)

}