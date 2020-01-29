package com.example.workoutapp.showworkout

import com.example.workoutapp.model.workout.WorkoutRepository
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers

class ShowWorkoutPresenter(
    private val workoutRepository: WorkoutRepository,
    private val compositeDisposable: CompositeDisposable
) : ShowWorkoutContract.Presenter {

    private lateinit var view: ShowWorkoutContract.View

    override fun setView(view: ShowWorkoutContract.View) {
        this.view = view
    }

    override fun start() {
        Observable.fromCallable { workoutRepository.getAllWorkouts() }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { workouts -> view.showWorkoutListData(workouts) }
            .addTo(compositeDisposable)
    }

    override fun finish() {
        compositeDisposable.clear()
    }

    override fun onWorkoutClicked(workoutId: Long) {
        view.showRoutines(workoutId)
    }

}