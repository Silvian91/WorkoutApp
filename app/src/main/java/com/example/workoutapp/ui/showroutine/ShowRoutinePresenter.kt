package com.example.workoutapp.ui.showroutine

import com.example.workoutapp.domain.extension.doOnIoObserveOnMain
import com.example.workoutapp.domain.routine.model.RoutineModel
import com.example.workoutapp.domain.showroutine.DeleteWorkoutUseCase
import com.example.workoutapp.domain.showroutine.DeleteWorkoutUseCase.Output.Success
import com.example.workoutapp.domain.showroutine.GetRoutineUseCase
import com.example.workoutapp.domain.showroutine.GetRoutineUseCase.Input
import com.example.workoutapp.ui.showroutine.adapter.ShowRoutineItemWrapper
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy

class ShowRoutinePresenter(
    private val deleteWorkoutUseCase: DeleteWorkoutUseCase,
    private val getRoutineUseCase: GetRoutineUseCase,
    private val compositeDisposable: CompositeDisposable
) : ShowRoutineContract.Presenter {

    private lateinit var view: ShowRoutineContract.View

    private var workoutId: Long = 0

    override fun start() {
        getRoutineUseCase.execute(Input(workoutId))
            .doOnIoObserveOnMain()
            .subscribeBy {
                when (it) {
                    is GetRoutineUseCase.Output.Success -> {
                        val routines = convertToItemWrappers(it.routines)
                        view.showRoutineData(routines)
                    }
                    is GetRoutineUseCase.Output.ErrorNoRoutines -> {
                        view.errorUnknown()
                    }
                }
            }
            .addTo(compositeDisposable)
    }

    private fun convertToItemWrappers(models: List<RoutineModel>): List<ShowRoutineItemWrapper> {
        val itemWrappers = ArrayList<ShowRoutineItemWrapper>()
        itemWrappers.add(ShowRoutineItemWrapper.Title("Routines"))

        models.forEach {
            itemWrappers.add(ShowRoutineItemWrapper.Entry(it))
        }

        return itemWrappers
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
        deleteWorkoutUseCase.execute(DeleteWorkoutUseCase.Input(workoutId))
            .doOnIoObserveOnMain()
            .subscribeBy {
                when (it) {
                    is Success -> view.nextActivity()
                    else -> view.errorUnknown()

                }
            }
            .addTo(compositeDisposable)
    }

}