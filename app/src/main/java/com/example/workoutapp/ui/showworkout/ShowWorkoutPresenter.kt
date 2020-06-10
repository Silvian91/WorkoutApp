package com.example.workoutapp.ui.showworkout

import com.example.workoutapp.domain.extension.doOnIoObserveOnMain
import com.example.workoutapp.domain.showroutine.DeleteWorkoutUseCase
import com.example.workoutapp.domain.showworkout.GetWorkoutUseCase
import com.example.workoutapp.domain.showworkout.GetWorkoutUseCase.Input
import com.example.workoutapp.domain.showworkout.GetWorkoutUseCase.Output.Success
import com.example.workoutapp.domain.showworkout.GetWorkoutUseCase.Output.SuccessNoData
import com.example.workoutapp.domain.user.GetCurrentUserUseCase
import com.example.workoutapp.domain.user.GetCurrentUserUseCase.Output.ErrorUnauthorized
import com.example.workoutapp.domain.workout.model.WorkoutModel
import com.example.workoutapp.ui.showworkout.adapter.ShowWorkoutItemWrapper
import com.example.workoutapp.ui.showworkout.adapter.ShowWorkoutItemWrapper.WorkoutNoData
import com.example.workoutapp.ui.showworkout.adapter.ShowWorkoutItemWrapper.WorkoutTitle
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import com.example.workoutapp.domain.user.GetCurrentUserUseCase.Output.Success as GetCurrentUserSuccess

class ShowWorkoutPresenter(
    private val deleteWorkoutUseCase: DeleteWorkoutUseCase,
    private val getWorkoutUseCase: GetWorkoutUseCase,
    private val getCurrentUserUseCase: GetCurrentUserUseCase,
    private val compositeDisposable: CompositeDisposable
) : ShowWorkoutContract.Presenter {

    private lateinit var view: ShowWorkoutContract.View

    override fun setView(view: ShowWorkoutContract.View) {
        this.view = view
    }

    override fun start() {
        getCurrentUserUseCase.execute(GetCurrentUserUseCase.Input)
            .doOnIoObserveOnMain()
            .subscribeBy {
                when (it) {
                    is GetCurrentUserSuccess -> getWorkoutsForUser(it.user.id!!)
                    is ErrorUnauthorized -> view.showLogin()
                    else -> view.showError()
                }
            }
            .addTo(compositeDisposable)
    }

    private fun getWorkoutsForUser(userId: Long) {
        getWorkoutUseCase.execute(Input(userId))
            .doOnIoObserveOnMain()
            .subscribeBy { output ->
                when (output) {
                    is SuccessNoData -> {
                        val items = convertToItemWrapper()
                        view.showWorkoutsListData(items)
                    }
                    is Success -> {
                        val items = convertToItemWrapper(output.workouts)
                        view.showWorkoutsListData(items)
                    }
                    else -> view.showError()
                }
            }
            .addTo(compositeDisposable)
    }

    private fun convertToItemWrapper(models: List<WorkoutModel> = emptyList()): List<ShowWorkoutItemWrapper> {
        val itemWrappers = ArrayList<ShowWorkoutItemWrapper>()
        if (models.isNotEmpty()) {
            models.forEach {
                itemWrappers.add(WorkoutTitle(it))
            }
        } else {
            itemWrappers.add(WorkoutNoData)
        }
        return itemWrappers
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