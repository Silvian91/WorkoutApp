package com.example.workoutapp.ui.error

sealed class ErrorType {

    object ErrorNameEmpty : UIError.UIErrorFeature()
    object ErrorSetsEmpty : UIError.UIErrorFeature()
    object ErrorRepsEmpty : UIError.UIErrorFeature()
    object ErrorWeightEmpty : UIError.UIErrorFeature()
    object ErrorWeightMeasurementEmpty : UIError.UIErrorFeature()
    object ErrorRestEmpty : UIError.UIErrorFeature()
    object ErrorWorkoutName : UIError.UIErrorFeature()

    object ErrorInvalidCredentials : UIError.UIErrorFeature()
    object ErrorUserNotExist : UIError.UIErrorFeature()

    object Unknown : UIError.UIErrorFeature()
    object NetworkError : UIError.UIErrorFeature()

    object ErrorRegistration : UIError.UIErrorFeature()

}

