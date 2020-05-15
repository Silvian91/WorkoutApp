package com.example.workoutapp.ui.addworkout

import com.example.workoutapp.domain.addworkout.AddWorkoutUseCase
import com.example.workoutapp.domain.addworkout.AddWorkoutUseCase.Input
import com.example.workoutapp.domain.addworkout.AddWorkoutUseCase.Output.Success
import com.example.workoutapp.domain.extension.doOnIoObserveOnMain
import com.example.workoutapp.domain.workout.model.WorkoutModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy

class AddWorkoutPresenter
    (
    private val addWorkoutUseCase: AddWorkoutUseCase,
    private val compositeDisposable: CompositeDisposable
) : AddWorkoutContract.Presenter {

    private lateinit var view: AddWorkoutContract.View

    override fun setView(view: AddWorkoutContract.View) {
        this.view = view
    }

    override fun start() {}

    private fun saveWorkout(workoutModel: WorkoutModel) {
        addWorkoutUseCase.execute(Input(workoutModel))
            .doOnIoObserveOnMain()
            .subscribeBy {
                when (it) {
                    is Success -> view.showAddRoutine(it.workoutId)
                    else -> view.errorUnknown()
                }
            }
            .addTo(compositeDisposable)
    }

    override fun onConfirmClicked(workoutTitle: String) {
        if (workoutTitle.isEmpty()) {
            view.showError()
        } else {
            saveWorkout(WorkoutModel(title = workoutTitle))
        }
    }

    override fun finish() = compositeDisposable.clear()

}