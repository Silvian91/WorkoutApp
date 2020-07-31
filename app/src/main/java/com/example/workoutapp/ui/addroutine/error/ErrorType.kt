package com.example.workoutapp.ui.addroutine.error

import com.example.workoutapp.ui.error.UIError

sealed class ErrorType {

    object ErrorNameEmpty : UIError.UIErrorFeature()
    object ErrorSetsEmpty : UIError.UIErrorFeature()
    object ErrorRepsEmpty : UIError.UIErrorFeature()
    object ErrorWeightEmpty : UIError.UIErrorFeature()
    object ErrorWeightMeasurementEmpty : UIError.UIErrorFeature()
    object ErrorRestEmpty : UIError.UIErrorFeature()
    object Unknown : UIError.UIErrorFeature()

}

