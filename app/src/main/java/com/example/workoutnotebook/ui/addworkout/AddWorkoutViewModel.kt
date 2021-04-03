package com.example.workoutnotebook.ui.addworkout

import com.example.workoutnotebook.domain.addworkout.AddWorkoutUseCase
import com.example.workoutnotebook.domain.addworkout.AddWorkoutUseCase.Input
import com.example.workoutnotebook.domain.extension.doOnIoObserveOnMain
import com.example.workoutnotebook.domain.user.GetCurrentUserUseCase
import com.example.workoutnotebook.domain.user.GetCurrentUserUseCase.Output.ErrorUnauthorized
import com.example.workoutnotebook.domain.workout.model.WorkoutModel
import com.example.core.ui.error.ErrorType.ErrorWorkoutName
import com.example.core.ui.error.ErrorType.Unknown
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.subjects.BehaviorSubject
import javax.inject.Inject
import com.example.core.ui.BaseViewModel as BaseViewModel
import com.example.workoutnotebook.domain.addworkout.AddWorkoutUseCase.Output.Success as SaveWorkoutSuccess
import com.example.workoutnotebook.domain.user.GetCurrentUserUseCase.Output.Success as GetUserSuccess

class AddWorkoutViewModel @Inject constructor (
    private val addWorkoutUseCase: AddWorkoutUseCase,
    private val getCurrentUserUseCase: GetCurrentUserUseCase
) : BaseViewModel() {

    private var userId: Long = 0

    val userUnauthorized = BehaviorSubject.create<Boolean>()
    val workout = BehaviorSubject.create<Long>()

    fun getUser() {
        getCurrentUserUseCase.execute(GetCurrentUserUseCase.Input)
            .doOnIoObserveOnMain()
            .subscribeBy {
                when (it) {
                    is GetUserSuccess -> userId = it.user.id!!
                    is ErrorUnauthorized -> userUnauthorized.onNext(true)
                    else -> error.onNext(Unknown)
                }
            }
            .addTo(compositeDisposable)
    }

    fun onConfirmClicked(workoutTitle: String) {
        if (workoutTitle.isEmpty()) {
            error.onNext(ErrorWorkoutName)
        } else {
            saveWorkout(WorkoutModel(title = workoutTitle, userId = userId))
        }
    }

    fun saveWorkout(workoutModel: WorkoutModel) {
        addWorkoutUseCase.execute(Input(workoutModel))
            .doOnIoObserveOnMain()
            .subscribeBy {
                when (it) {
                    is SaveWorkoutSuccess -> workout.onNext(it.workoutId)
                    else -> error.onNext(Unknown)
                }
            }
            .addTo(compositeDisposable)
    }

}