package com.example.workoutapp.ui.addroutine

import com.example.workoutapp.data.database.routine.RoutineRepository
import com.example.workoutapp.data.database.workout.WorkoutRepository
import com.example.workoutapp.domain.extension.doOnIoObserveOnMain
import com.example.workoutapp.domain.routine.model.RoutineModel
import com.example.workoutapp.ui.addroutine.AddRoutineContract.ErrorType.*
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo

class AddRoutinePresenter(
    private val routineRepository: RoutineRepository,
    private val workoutRepository: WorkoutRepository,
    private val compositeDisposable: CompositeDisposable
) :
    AddRoutineContract.Presenter {

    private lateinit var view: AddRoutineContract.View
    private var workoutId: Long = 0
    private val routinePairs = ArrayList<RoutineModel>()

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
        routine_name: String,
        routine_reps: String,
        routine_sets: String,
        routine_weight: String,
        routine_weight_measurement: String,
        routine_rest: String,
        workoutId: Long
    ) {
        routinePairs.add(
            RoutineModel(
                routine_name,
                routine_sets,
                routine_reps,
                routine_weight,
                routine_weight_measurement,
                routine_rest,
                workoutId
            )
        )
    }

    private fun saveRoutines(routinePairs: ArrayList<RoutineModel>) {
        routineRepository.insertRoutine(routinePairs)
            .doOnIoObserveOnMain()
            .subscribe { view.nextActivity() }
            .addTo(compositeDisposable)
    }

    private fun addRoutinePairsOrShowError(
        routine_name: String, routine_sets: String, routine_reps: String,
        routine_weight: String, routine_weight_measurement: String, routine_rest: String
    ): Boolean {
        when {
            routine_name.isEmpty() -> {
                view.showError(NAME_EMPTY)
                return false
            }
            routine_sets.isEmpty() -> {
                view.showError(SETS_EMPTY)
                return false
            }
            routine_reps.isEmpty() -> {
                view.showError(REPS_EMPTY)
                return false
            }
            routine_weight.isEmpty() -> {
                view.showError(WEIGHT_EMPTY)
                return false
            }
            routine_weight_measurement.isEmpty() -> {
                view.showError(WEIGHT_MEASUREMENT_EMPTY)
                return false
            }
            routine_rest.isEmpty() -> {
                view.showError(REST_EMPTY)
                return false
            }
            else -> {
                addRoutinePairs(
                    routine_name,
                    routine_sets,
                    routine_reps,
                    routine_weight,
                    routine_weight_measurement,
                    routine_rest,
                    workoutId
                )
                return true
            }
        }
    }

    override fun onContinueClicked(
        routine_name: String, routine_sets: String, routine_reps: String,
        routine_weight: String, routine_weight_measurement: String, routine_rest: String
    ) {
        if (addRoutinePairsOrShowError(
                routine_name,
                routine_sets,
                routine_reps,
                routine_weight,
                routine_weight_measurement,
                routine_rest
            )
        ) {
            view.clearAllInputFields()
            view.resetFocus()
        }

    }

    override fun onFinishClicked(
        routine_name: String, routine_sets: String, routine_reps: String,
        routine_weight: String, routine_weight_measurement: String, routine_rest: String
    ) {
        if (addRoutinePairsOrShowError(
                routine_name,
                routine_sets,
                routine_reps,
                routine_weight,
                routine_weight_measurement,
                routine_rest
            )
        ) {
            saveRoutines(routinePairs)
        }

    }

    override fun onBackClicked(
        routine_name: String, routine_sets: String, routine_reps: String,
        routine_weight: String, routine_weight_measurement: String, routine_rest: String
    ) {
        if (routine_name.isEmpty() || routine_sets.isEmpty() || routine_reps.isEmpty() || routine_weight.isEmpty() || routine_weight_measurement.isEmpty() ||
            routine_rest.isEmpty()
        ) {
            workoutRepository.deleteRoutine(workoutId)
                .doOnIoObserveOnMain()
                .subscribe { view.nextActivity() }
                .addTo(compositeDisposable)
        } else {
            addRoutinePairs(
                routine_name,
                routine_sets,
                routine_reps,
                routine_weight,
                routine_weight_measurement,
                routine_rest,
                workoutId
            )
            saveRoutines(routinePairs)
        }
    }
}


