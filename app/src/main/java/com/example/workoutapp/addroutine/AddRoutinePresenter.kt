package com.example.workoutapp.addroutine

import com.example.workoutapp.addroutine.AddRoutineContract.ErrorType.*
import com.example.workoutapp.model.routine.RoutineEntity
import com.example.workoutapp.model.routine.RoutineRepository
import com.example.workoutapp.model.workout.WorkoutRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers

class AddRoutinePresenter(
    private val routineRepository: RoutineRepository,
    private val workoutRepository: WorkoutRepository,
    private val compositeDisposable: CompositeDisposable
) :
    AddRoutineContract.Presenter {

    private lateinit var view: AddRoutineContract.View
    private var workoutId: Long = 0
    private val routinePairs = ArrayList<RoutineEntity>()

    override fun setView(view: AddRoutineContract.View) {
        this.view = view
    }

    override fun start() {
    }

    override fun finish() {
        compositeDisposable.clear()
    }

    override fun setWorkoutId(workoutId: Long) {
        this.workoutId = workoutId
    }

    private fun addRoutinePairs(
        routine_name: String, routine_reps: String, routine_sets: String,
        routine_weight: String, routine_rest: String, workoutId: Long
    ) {
        routinePairs.add(
            RoutineEntity(
                routine_name,
                routine_sets,
                routine_reps,
                routine_rest,
                routine_weight,
                workoutId
            )
        )
    }

    private fun saveRoutines(routinePairs: ArrayList<RoutineEntity>) {
        routineRepository.insertRoutine(routinePairs)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { finish() }
            .addTo(compositeDisposable)
    }

    override fun onContinueClicked(
        routine_name: String, routine_sets: String, routine_reps: String,
        routine_weight: String, routine_rest: String
    ) {
        when {
            routine_name.isEmpty() -> {
                view.showError(NAME_EMPTY)
            }
            routine_sets.isEmpty() -> {
                view.showError(SETS_EMPTY)
            }
            routine_reps.isEmpty() -> {
                view.showError(REPS_EMPTY)
            }
            routine_weight.isEmpty() -> {
                view.showError(WEIGHT_EMPTY)
            }
            routine_rest.isEmpty() -> {
                view.showError(REST_EMPTY)
            }
            else -> {
                addRoutinePairs(
                    routine_name,
                    routine_sets,
                    routine_reps,
                    routine_rest,
                    routine_weight,
                    workoutId
                )
                view.clearAllInputFields()
                view.resetFocus()
            }
        }
    }

    override fun onFinishClicked(
        routine_name: String, routine_sets: String, routine_reps: String,
        routine_weight: String, routine_rest: String
    ) {
        when {
            routine_name.isEmpty() -> {
                view.showError(NAME_EMPTY)
            }
            routine_sets.isEmpty() -> {
                view.showError(SETS_EMPTY)
            }
            routine_reps.isEmpty() -> {
                view.showError(REPS_EMPTY)
            }
            routine_weight.isEmpty() -> {
                view.showError(WEIGHT_EMPTY)
            }
            routine_rest.isEmpty() -> {
                view.showError(REST_EMPTY)
            }
            else -> {
                addRoutinePairs(
                    routine_name,
                    routine_sets,
                    routine_reps,
                    routine_rest,
                    routine_weight,
                    workoutId
                )
                saveRoutines(routinePairs)
                view.nextActivity()
            }
        }
    }

    override fun onBackClicked(
        routine_name: String, routine_sets: String, routine_reps: String,
        routine_weight: String, routine_rest: String
    ) {
        if (routine_name.isEmpty() || routine_sets.isEmpty() || routine_reps.isEmpty() || routine_weight.isEmpty() || routine_rest.isEmpty()) {
            workoutRepository.deleteRoutine(workoutId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { finish() }
                .addTo(compositeDisposable)
            view.nextActivity()
        } else {
            addRoutinePairs(
                routine_name,
                routine_sets,
                routine_reps,
                routine_rest,
                routine_weight,
                workoutId
            )
            saveRoutines(routinePairs)
            view.nextActivity()
        }
    }

}


