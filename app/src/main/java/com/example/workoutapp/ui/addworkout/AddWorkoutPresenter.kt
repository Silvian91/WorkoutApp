package com.example.workoutapp.ui.addworkout

import com.example.workoutapp.domain.extension.doOnIoObserveOnMain
import com.example.workoutapp.domain.workout.WorkoutRepository
import com.example.workoutapp.domain.workout.model.WorkoutModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy

class AddWorkoutPresenter
    (
    private val compositeDisposable: CompositeDisposable,
    private val workoutRepository: WorkoutRepository
) : AddWorkoutContract.Presenter {

    private lateinit var view: AddWorkoutContract.View


    override fun setView(view: AddWorkoutContract.View) {
        this.view = view
    }

    override fun start() {
    }

    private fun saveWorkout(workoutModel: WorkoutModel) {
        workoutRepository.insertWorkout(workoutModel)
            .doOnIoObserveOnMain()
            .subscribeBy {
                view.showAddRoutine(it)
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

    override fun finish() {
        compositeDisposable.clear()
    }

}