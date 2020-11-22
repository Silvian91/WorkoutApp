package com.example.workoutapp.ui.showroutine

import com.example.workoutapp.domain.extension.doOnIoObserveOnMain
import com.example.workoutapp.domain.routine.model.RoutineModel
import com.example.workoutapp.domain.showroutine.DeleteWorkoutUseCase
import com.example.workoutapp.domain.showroutine.GetRoutineUseCase
import com.example.workoutapp.domain.showroutine.GetRoutineUseCase.Input
import com.example.workoutapp.domain.showroutine.GetRoutineUseCase.Output.ErrorNoRoutines
import com.example.core.ui.BaseViewModel
import com.example.core.ui.error.ErrorType.Unknown
import com.example.workoutapp.ui.showroutine.adapter.ShowRoutineItemWrapper
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.subjects.BehaviorSubject
import javax.inject.Inject
import com.example.workoutapp.domain.showroutine.DeleteWorkoutUseCase.Output.Success as DeleteWorkoutSuccess
import com.example.workoutapp.domain.showroutine.GetRoutineUseCase.Output.Success as GetRoutineSuccess

class ShowRoutineViewModel @Inject constructor(
    private val deleteWorkoutUseCase: DeleteWorkoutUseCase,
    private val getRoutineUseCase: GetRoutineUseCase
) : BaseViewModel() {

    private var workoutId: Long = 0

    val routineData = BehaviorSubject.create<List<ShowRoutineItemWrapper>>()
    val showWorkout = BehaviorSubject.create<Boolean>()
    val delete = BehaviorSubject.create<Long>()

     fun getRoutine() {
        getRoutineUseCase.execute(Input(workoutId))
            .doOnIoObserveOnMain()
            .subscribeBy {
                when (it) {
                    is GetRoutineSuccess -> {
                        val routines = convertToItemWrappers(it.routines)
                        routineData.onNext(routines)
                    }
                    is ErrorNoRoutines -> {
                        error.onNext(Unknown)
                    }
                }
            }
            .addTo(compositeDisposable)
    }

    private fun convertToItemWrappers(models: List<RoutineModel>): List<ShowRoutineItemWrapper> {
        val itemWrappers = ArrayList<ShowRoutineItemWrapper>()
        itemWrappers.add(ShowRoutineItemWrapper.Title(ROUTINES))

        models.forEach {
            itemWrappers.add(ShowRoutineItemWrapper.Entry(it))
        }

        return itemWrappers
    }

    fun setWorkoutId(workoutId: Long) {
        this.workoutId = workoutId
    }

    fun onDeleteConfirmed(workoutId: Long) {
        deleteWorkoutUseCase.execute(DeleteWorkoutUseCase.Input(workoutId))
            .doOnIoObserveOnMain()
            .subscribeBy {
                when (it) {
                    is DeleteWorkoutSuccess -> showWorkout.onNext(true)
                    else -> error.onNext(Unknown)
                }
            }
            .addTo(compositeDisposable)
    }

    fun onDeleteClicked() = delete.onNext(workoutId)


    companion object {
        const val ROUTINES = "Routines"
    }

}