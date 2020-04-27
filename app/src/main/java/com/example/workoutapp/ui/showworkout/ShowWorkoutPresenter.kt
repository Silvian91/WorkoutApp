package com.example.workoutapp.ui.showworkout

import com.example.workoutapp.domain.extension.doOnIoObserveOnMain
import com.example.workoutapp.domain.showroutine.DeleteWorkoutUseCase
import com.example.workoutapp.domain.showworkout.GetWorkoutUseCase
import com.example.workoutapp.domain.showworkout.GetWorkoutUseCase.Input
import com.example.workoutapp.domain.showworkout.GetWorkoutUseCase.Output.Success
import com.example.workoutapp.domain.showworkout.GetWorkoutUseCase.Output.SuccessNoData
import com.example.workoutapp.domain.workout.model.WorkoutModel
import com.example.workoutapp.ui.showworkout.adapter.ShowWorkoutItemWrapper
import com.example.workoutapp.ui.showworkout.adapter.ShowWorkoutItemWrapper.WorkoutNoData
import com.example.workoutapp.ui.showworkout.adapter.ShowWorkoutItemWrapper.WorkoutTitle
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy

class ShowWorkoutPresenter(
    private val deleteWorkoutUseCase: DeleteWorkoutUseCase,
    private val getWorkoutUseCase: GetWorkoutUseCase,
    private val compositeDisposable: CompositeDisposable
) : ShowWorkoutContract.Presenter {

    private lateinit var view: ShowWorkoutContract.View

    override fun setView(view: ShowWorkoutContract.View) {
        this.view = view
    }

    override fun start() {
        getWorkoutUseCase.execute(Input)
            .doOnIoObserveOnMain()
            .subscribeBy {
                when (it) {
                    is SuccessNoData -> {
                        val items = convertToItemWrapper()
                        view.showWorkoutsListData(items)
                    }
                    is Success -> {
                        val items = convertToItemWrapper(it.workouts)
                        view.showWorkoutsListData(items)
                    }
                    else -> view.showError()
                }
            }
            .addTo(compositeDisposable)
    }

    private fun convertToItemWrapper(models: List<WorkoutModel> = emptyList()): List<ShowWorkoutItemWrapper> {
        val itemWrappers = ArrayList<ShowWorkoutItemWrapper>()
        return if (models.isNotEmpty()) {
            models.forEach {
                itemWrappers.add(WorkoutTitle(it))
            }
            itemWrappers
        } else {
            itemWrappers.add(WorkoutNoData)
            itemWrappers
        }
    }

    override fun onDeleteClicked(
        workoutId: Long,
        workoutsList: List<ShowWorkoutItemWrapper>
    ) {
        deleteWorkoutUseCase.execute(DeleteWorkoutUseCase.Input(workoutId))
            .doOnIoObserveOnMain()
            .subscribeBy {
                when (it) {
                   is DeleteWorkoutUseCase.Output.Success -> view.showWorkoutsListData(workoutsList)
                    else -> view.showError()
                }
            }
            .addTo(compositeDisposable)
    }

    override fun finish() = compositeDisposable.clear()

    override fun onWorkoutClicked(workoutId: Long) = view.showRoutines(workoutId)

    override fun onSwipeToDelete(
        workoutId: Long,
        workoutsList: List<ShowWorkoutItemWrapper>
    ) {
        view.alertDialog(workoutId, workoutsList)
    }

}