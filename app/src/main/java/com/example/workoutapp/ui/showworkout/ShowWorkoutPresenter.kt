package com.example.workoutapp.ui.showworkout

import com.example.workoutapp.domain.extension.doOnIoObserveOnMain
import com.example.workoutapp.domain.showroutine.DeleteWorkoutUseCase
import com.example.workoutapp.domain.showworkout.GetWorkoutUseCase
import com.example.workoutapp.domain.showworkout.GetWorkoutUseCase.Input
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
import com.example.workoutapp.domain.showroutine.DeleteWorkoutUseCase.Output.Success as DeleteWorkoutSuccess
import com.example.workoutapp.domain.showworkout.GetWorkoutUseCase.Output.Success as GetWorkoutSuccess
import com.example.workoutapp.domain.user.GetCurrentUserUseCase.Output.Success as GetUserSuccess

class ShowWorkoutPresenter(
    private val deleteWorkoutUseCase: DeleteWorkoutUseCase,
    private val getWorkoutUseCase: GetWorkoutUseCase,
    private val getCurrentUserUseCase: GetCurrentUserUseCase,
    private val compositeDisposable: CompositeDisposable
) : ShowWorkoutContract.Presenter {

    private lateinit var view: ShowWorkoutContract.View

    private var userId: Long = 0

    override fun setView(view: ShowWorkoutContract.View) {
        this.view = view
    }

    override fun start() {
        getCurrentUserUseCase.execute(GetCurrentUserUseCase.Input)
            .doOnIoObserveOnMain()
            .subscribeBy {
                when (it) {
                    is GetUserSuccess -> {
                        userId = it.user.id!!
                        getWorkoutsForUser(userId)
                    }
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
                        view.showWorkouts(items)
                    }
                    is GetWorkoutSuccess -> {
                        val items = convertToItemWrapper(output.workouts)
                        view.showWorkouts(items)
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

    override fun onDeleteClicked(workoutId: Long) {
        deleteWorkoutUseCase.execute(DeleteWorkoutUseCase.Input(workoutId))
            .doOnIoObserveOnMain()
            .subscribeBy {
                when (it) {
                    is DeleteWorkoutSuccess -> {
                        getWorkoutsForUser(userId)
                        view.showUndoOption(workoutId)
                    }
                    else -> view.showError()
                }
            }
            .addTo(compositeDisposable)
    }

    override fun onUndoDeletion(workoutId: Long) {
        //TODO: Undo deletion
    }

    override fun onRetryClicked() {
        start()
    }

    override fun finish() = compositeDisposable.clear()

    override fun onWorkoutClicked(workoutId: Long) = view.showRoutines(workoutId)

    override fun onDeleteWorkout(workoutId: Long) {
        view.showDeleteConfirmation(workoutId)
    }

}