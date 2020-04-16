package com.example.workoutapp.domain.common

import com.example.workoutapp.domain.common.BaseUseCase.Input
import com.example.workoutapp.domain.common.BaseUseCase.Output
import io.reactivex.Single

interface BaseSingleUseCase<SINGLE_IN: Input, SINGLE_OUT: Output> : BaseUseCase<SINGLE_IN, SINGLE_OUT> {

    fun execute(input: SINGLE_IN): Single<SINGLE_OUT>
}