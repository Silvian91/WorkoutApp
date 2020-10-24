package com.example.workoutapp.ui.consent

import com.example.workoutapp.R
import com.example.workoutapp.R.string.text_consent_body
import com.example.workoutapp.R.string.text_consent_header
import com.example.workoutapp.ui.common.BaseViewModel
import com.example.workoutapp.ui.consent.adapter.ConsentItemWrapper
import io.reactivex.subjects.BehaviorSubject
import javax.inject.Inject

class ConsentViewModel @Inject constructor(

): BaseViewModel() {

    private var items = mutableListOf(
        ConsentItemWrapper.Header(text_consent_header),
        ConsentItemWrapper.Body(text_consent_body),
        ConsentItemWrapper.Actions
    )

    private var currentViewState = ConsentViewState(
        items,
        accepted = false,
        denied = false
    )

    val viewState = BehaviorSubject.createDefault(currentViewState)

    fun setData() {
        currentViewState = currentViewState.copy(items = items, accepted = false, denied = false)
        viewState.onNext(currentViewState)
    }

    fun acceptedConsent() {
        currentViewState = currentViewState.copy(accepted = true)
        viewState.onNext(currentViewState)
    }

    fun deniedConsent() {
        currentViewState = currentViewState.copy(denied = true)
        viewState.onNext(currentViewState)
    }

}