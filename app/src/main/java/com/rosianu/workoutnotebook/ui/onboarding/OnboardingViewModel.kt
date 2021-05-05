package com.rosianu.workoutnotebook.ui.onboarding

import com.rosianu.core.ui.BaseViewModel
import io.reactivex.subjects.BehaviorSubject
import javax.inject.Inject

class OnboardingViewModel @Inject constructor() : BaseViewModel() {

    val register = BehaviorSubject.create<Boolean>()

    fun registerClicked() = register.onNext(true)

}