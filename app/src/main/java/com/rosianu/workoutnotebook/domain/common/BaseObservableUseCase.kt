package com.rosianu.workoutnotebook.domain.common

import com.rosianu.workoutnotebook.domain.common.BaseUseCase.Input
import com.rosianu.workoutnotebook.domain.common.BaseUseCase.Output
import io.reactivex.Observable

interface BaseObservableUseCase<SINGLE_IN : Input, SINGLE_OUT : Output> :
    BaseUseCase<SINGLE_IN, SINGLE_OUT> {

    fun execute(input: SINGLE_IN): Observable<SINGLE_OUT>
}