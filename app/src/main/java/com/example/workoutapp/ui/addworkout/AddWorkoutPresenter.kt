package com.example.workoutapp.ui.addworkout

import com.example.workoutapp.domain.addworkout.AddWorkoutUseCase
import com.example.workoutapp.domain.addworkout.AddWorkoutUseCase.Input
import com.example.workoutapp.domain.extension.doOnIoObserveOnMain
import com.example.workoutapp.domain.user.GetCurrentUserUseCase
import com.example.workoutapp.domain.user.GetCurrentUserUseCase.Output.ErrorUnauthorized
import com.example.workoutapp.domain.workout.model.WorkoutModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import com.example.workoutapp.domain.addworkout.AddWorkoutUseCase.Output.Success as SaveWorkoutSuccess
import com.example.workoutapp.domain.user.GetCurrentUserUseCase.Output.Success as GetUserSuccess

class AddWorkoutPresenter
    (
    private val addWorkoutUseCase: AddWorkoutUseCase,
    private val getCurrentUserUseCase: GetCurrentUserUseCase,
    private val compositeDisposable: CompositeDisposable
) : AddWorkoutContract.Presenter {

    private lateinit var view: AddWorkoutContract.View

    private var userId: Long = 0

    override fun setView(view: AddWorkoutContract.View) {
        this.view = view
    }

    override fun start() {
        getCurrentUserUseCase.execute(GetCurrentUserUseCase.Input)
            .doOnIoObserveOnMain()
            .subscribeBy {
                when (it) {
                    is GetUserSuccess -> userId = it.user.id!!
                    is ErrorUnauthorized -> view.showLogin()
                    else -> view.showError()
                }
            }
            .addTo(compositeDisposable)
    }

    override fun onConfirmClicked(workoutTitle: String) {
        if (workoutTitle.isEmpty()) {
            view.showError()
        } else {
            saveWorkout(WorkoutModel(title = workoutTitle, userId = userId))
        }
    }

    private fun saveWorkout(workoutModel: WorkoutModel) {
        addWorkoutUseCase.execute(Input(workoutModel))
            .doOnIoObserveOnMain()
            .subscribeBy {
                when (it) {
                    is SaveWorkoutSuccess -> view.showAddRoutine(it.workoutId)
                    else -> view.errorUnknown()
                }
            }
            .addTo(compositeDisposable)
    }

    override fun finish() = compositeDisposable.clear()

}