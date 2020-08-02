package com.example.workoutapp.ui.addroutine

import com.example.workoutapp.domain.addroutine.DeleteRoutineUseCase
import com.example.workoutapp.domain.addroutine.DeleteRoutineUseCase.Input
import com.example.workoutapp.domain.addroutine.SaveRoutineUseCase
import com.example.workoutapp.domain.extension.doOnIoObserveOnMain
import com.example.workoutapp.domain.routine.model.RoutineModel
import com.example.workoutapp.ui.addroutine.error.ErrorType.*
import com.example.workoutapp.ui.common.BaseViewModel
import com.example.workoutapp.ui.error.UIError
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.subjects.BehaviorSubject
import javax.inject.Inject
import com.example.workoutapp.domain.addroutine.DeleteRoutineUseCase.Output.Success as DeleteRoutineSuccess
import com.example.workoutapp.domain.addroutine.SaveRoutineUseCase.Output.Success as SaveRoutineSuccess

class AddRoutineViewModel @Inject constructor(
    private val saveRoutineUseCase: SaveRoutineUseCase,
    private val deleteRoutineUseCase: DeleteRoutineUseCase
) : BaseViewModel() {

    private var workoutId: Long = 0
    private val routinePairs = ArrayList<RoutineModel>()

    val routines = BehaviorSubject.create<Boolean>()
    val continueClicked = BehaviorSubject.create<Boolean>()
    val finishClicked = BehaviorSubject.create<Boolean>()

    fun setWorkoutId(workoutId: Long) {
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

    private fun saveRoutines(routinePairs: List<RoutineModel>) {
        saveRoutineUseCase.execute(SaveRoutineUseCase.Input(routinePairs))
            .doOnIoObserveOnMain()
            .subscribeBy {
                when (it) {
                    is SaveRoutineSuccess -> routines.onNext(true)
                    else -> error.onNext(Unknown)
                }
            }
            .addTo(compositeDisposable)
    }

    private fun addRoutinePairsOrShowError(
        routine_name: String, routine_sets: String, routine_reps: String,
        routine_weight: String, routine_weight_measurement: String, routine_rest: String
    ): Boolean {

        var isValid = true

        when {
            routine_name.isEmpty() -> {
                error.onNext(ErrorNameEmpty)
                isValid = false
            }
            routine_sets.isEmpty() -> {
                error.onNext(ErrorSetsEmpty)
                isValid = false
            }
            routine_reps.isEmpty() -> {
                error.onNext(ErrorRepsEmpty)
                isValid = false
            }
            routine_weight.isEmpty() -> {
                error.onNext(ErrorWeightEmpty)
                isValid = false
            }
            routine_weight_measurement.isEmpty() -> {
                error.onNext(ErrorWeightMeasurementEmpty)
                isValid = false
            }
            routine_rest.isEmpty() -> {
                error.onNext(ErrorRestEmpty)
                isValid = false
            }
        }

        if (isValid) {
            addRoutinePairs(
                routine_name,
                routine_sets,
                routine_reps,
                routine_weight,
                routine_weight_measurement,
                routine_rest,
                workoutId
            )
        }
        return isValid
    }

    fun onContinueClicked(
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
            continueClicked.onNext(true)
        }

    }

    fun onFinishClicked(
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
            finishClicked.onNext(true)
        }

    }

    fun onBackClicked(
        routine_name: String, routine_sets: String, routine_reps: String,
        routine_weight: String, routine_weight_measurement: String, routine_rest: String
    ) {
        if (routine_name.isEmpty() || routine_sets.isEmpty() || routine_reps.isEmpty() || routine_weight.isEmpty() || routine_weight_measurement.isEmpty() ||
            routine_rest.isEmpty()
        ) {
            deleteRoutineUseCase.execute(Input(workoutId))
                .doOnIoObserveOnMain()
                .subscribeBy {
                    when (it) {
                        is DeleteRoutineSuccess -> routines.onNext(true)
                        else -> error.onNext(Unknown)
                    }
                }
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