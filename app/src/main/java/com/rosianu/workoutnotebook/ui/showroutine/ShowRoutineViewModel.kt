package com.rosianu.workoutnotebook.ui.showroutine

import com.rosianu.core.ui.BaseViewModel
import com.rosianu.core.ui.error.ErrorType.Unknown
import com.rosianu.workoutnotebook.domain.extension.doOnIoObserveOnMain
import com.rosianu.workoutnotebook.domain.routine.model.RoutineModel
import com.rosianu.workoutnotebook.domain.showroutine.DeleteWorkoutUseCase
import com.rosianu.workoutnotebook.domain.showroutine.GetRoutineUseCase
import com.rosianu.workoutnotebook.domain.showroutine.GetRoutineUseCase.Input
import com.rosianu.workoutnotebook.domain.showroutine.GetRoutineUseCase.Output.ErrorNoRoutines
import com.rosianu.workoutnotebook.domain.showroutine.GetTitleUseCase
import com.rosianu.workoutnotebook.domain.workout.model.WorkoutModel
import com.rosianu.workoutnotebook.ui.showroutine.adapter.ShowRoutineItemWrapper
import com.rosianu.workoutnotebook.ui.showroutine.adapter.ShowRoutineItemWrapper.Entry
import com.rosianu.workoutnotebook.ui.showroutine.adapter.ShowRoutineItemWrapper.Title
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.subjects.BehaviorSubject
import javax.inject.Inject
import com.rosianu.workoutnotebook.domain.showroutine.DeleteWorkoutUseCase.Output.Success as DeleteWorkoutSuccess
import com.rosianu.workoutnotebook.domain.showroutine.GetRoutineUseCase.Output.Success as GetRoutineSuccess
import com.rosianu.workoutnotebook.domain.showroutine.GetTitleUseCase.Output.ErrorNoTitle as ErrorNoTitle
import com.rosianu.workoutnotebook.domain.showroutine.GetTitleUseCase.Output.Success as TitleSuccess

class ShowRoutineViewModel @Inject constructor(
    private val deleteWorkoutUseCase: DeleteWorkoutUseCase,
    private val getRoutineUseCase: GetRoutineUseCase,
    private val getTitleUseCase: GetTitleUseCase
) : BaseViewModel() {

    private var workoutId: Long = 0

    val workoutTitle = BehaviorSubject.create<List<ShowRoutineItemWrapper>>()
    val workoutData = BehaviorSubject.create<List<ShowRoutineItemWrapper>>()
    val showWorkout = BehaviorSubject.create<Boolean>()
    val delete = BehaviorSubject.create<Long>()

    fun getTitle() {
        getTitleUseCase.execute(GetTitleUseCase.Input(workoutId))
            .doOnIoObserveOnMain()
            .subscribeBy {
                when (it) {
                    is TitleSuccess -> {
                        val title = convertTitleToItemWrappers(it.workoutModel)
                        workoutTitle.onNext(title)
                    }
                    is ErrorNoTitle -> {
                        error.onNext(Unknown)
                    }
                }
            }
            .addTo(compositeDisposable)
    }

    private fun convertTitleToItemWrappers(models: List<WorkoutModel>): List<ShowRoutineItemWrapper> {
        val itemWrappers = ArrayList<ShowRoutineItemWrapper>()
        models.forEach {
            itemWrappers.add(Title(it))
        }
        return itemWrappers
    }

    fun getRoutine() {
        getRoutineUseCase.execute(Input(workoutId))
            .doOnIoObserveOnMain()
            .subscribeBy {
                when (it) {
                    is GetRoutineSuccess -> {
                        val routines = convertRoutinesToItemWrappers(it.routines)
                        workoutData.onNext(routines)
                    }
                    is ErrorNoRoutines -> {
                        error.onNext(Unknown)
                    }
                }
            }
            .addTo(compositeDisposable)
    }

    private fun convertRoutinesToItemWrappers(models: List<RoutineModel>): List<ShowRoutineItemWrapper> {
        val itemWrappers = ArrayList<ShowRoutineItemWrapper>()
        models.forEach {
            itemWrappers.add(Entry(it))
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


}