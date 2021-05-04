package com.example.workoutnotebook.ui.editroutine

import com.example.core.ui.BaseViewModel
import com.example.core.ui.error.ErrorType
import com.example.workoutnotebook.domain.extension.doOnIoObserveOnMain
import com.example.workoutnotebook.domain.routine.model.RoutineModel
import com.example.workoutnotebook.domain.showroutine.GetRoutineUseCase
import com.example.workoutnotebook.ui.editroutine.adapter.EditRoutineItemWrapper
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.subjects.BehaviorSubject
import javax.inject.Inject

class EditRoutineViewModel @Inject constructor(
    private val getRoutineUseCase: GetRoutineUseCase
) : BaseViewModel() {

    private var workoutId: Long = 0

    val routinesData = BehaviorSubject.create<List<EditRoutineItemWrapper>>()

    private fun getRoutine() {
        getRoutineUseCase.execute(GetRoutineUseCase.Input(workoutId))
            .doOnIoObserveOnMain()
            .subscribeBy {
                when (it) {
                    is GetRoutineUseCase.Output.Success -> {
                        val routines = convertRoutinesToItemWrappers(it.routines)
                        routinesData.onNext(routines)
                    }
                    else -> {
                        error.onNext(ErrorType.Unknown)
                    }
                }
            }
            .addTo(compositeDisposable)
    }

    private fun convertRoutinesToItemWrappers(models: List<RoutineModel>): List<EditRoutineItemWrapper> {
        val itemWrappers = ArrayList<EditRoutineItemWrapper>()
        models.forEach {
            itemWrappers.add(EditRoutineItemWrapper.Routine(it))
        }
        return itemWrappers
    }

    fun initData(workoutId: Long) {
        this.workoutId = workoutId
        getRoutine()
    }

}