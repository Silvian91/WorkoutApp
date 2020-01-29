package com.example.workoutapp.showroutine

import com.example.workoutapp.model.routine.RoutineRepository
import com.example.workoutapp.model.workout.WorkoutRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers

class ShowRoutinePresenter(
    private val routineRepository: RoutineRepository,
    private val workoutRepository: WorkoutRepository,
    private val compositeDisposable : CompositeDisposable
) : ShowRoutineContract.Presenter {

    private lateinit var view: ShowRoutineContract.View

    private var workoutId: Long = 0

    override fun start() {
        routineRepository.getRoutine(workoutId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { routineData -> view.showRoutineData(routineData) }
            .addTo(compositeDisposable)
    }

    override fun finish() {
        compositeDisposable.clear()
    }

    override fun setView(view: ShowRoutineContract.View) {
        this.view = view
    }

    override fun setWorkoutId(workoutId: Long) {
        this.workoutId = workoutId
    }

    override fun onDeleteClicked(workoutId: Long) {
        workoutRepository.deleteRoutine(workoutId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { finish() }
            .addTo(compositeDisposable)
        view.nextActivity()
    }

}