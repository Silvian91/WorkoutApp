package com.example.workoutnotebook.domain.common

import com.example.workoutnotebook.domain.common.BaseUseCase.Input
import com.example.workoutnotebook.domain.common.BaseUseCase.Output
import io.reactivex.Observable
import io.reactivex.Single

interface BaseObservableUseCase<SINGLE_IN : Input, SINGLE_OUT : Output> :
    BaseUseCase<SINGLE_IN, SINGLE_OUT> {

    fun execute(input: SINGLE_IN): Observable<SINGLE_OUT>
}