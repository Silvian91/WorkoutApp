package com.example.workoutnotebook.domain.common

interface BaseUseCase<IN : BaseUseCase.Input, OUT : BaseUseCase.Output> {

    interface Input

    interface Output

}