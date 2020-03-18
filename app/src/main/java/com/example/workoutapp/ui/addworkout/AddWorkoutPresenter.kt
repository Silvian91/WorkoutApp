package com.example.workoutapp.ui.addworkout

import com.example.workoutapp.data.workout.WorkoutRepository
import com.example.workoutapp.domain.workout.model.WorkoutModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class AddWorkoutPresenter
    (
    private val workoutRepository: WorkoutRepository,
    private val compositeDisposable: CompositeDisposable
) : AddWorkoutContract.Presenter {

    private lateinit var view: AddWorkoutContract.View


    override fun setView(view: AddWorkoutContract.View) {
        this.view = view
    }

    override fun start() {
    }

    override fun finish() {
        compositeDisposable.clear()
    }

    private fun saveWorkout(workoutModel: WorkoutModel) {
        workoutRepository.insertWorkout(workoutModel)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
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

}