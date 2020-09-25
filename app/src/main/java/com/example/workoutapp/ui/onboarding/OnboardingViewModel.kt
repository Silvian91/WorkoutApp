package com.example.workoutapp.ui.onboarding

import com.example.workoutapp.ui.common.BaseViewModel
import io.reactivex.subjects.BehaviorSubject
import javax.inject.Inject

class OnboardingViewModel @Inject constructor() : BaseViewModel() {

    val register = BehaviorSubject.create<Boolean>()

    fun registerClicked() = register.onNext(true)

}