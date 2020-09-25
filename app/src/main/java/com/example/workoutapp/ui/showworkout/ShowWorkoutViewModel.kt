package com.example.workoutapp.ui.showworkout

import com.example.workoutapp.domain.extension.doOnIoObserveOnMain
import com.example.workoutapp.domain.showworkout.GetWorkoutUseCase
import com.example.workoutapp.domain.showworkout.GetWorkoutUseCase.Input
import com.example.workoutapp.domain.showworkout.GetWorkoutUseCase.Output.SuccessNoData
import com.example.workoutapp.domain.showworkout.SoftDeleteWorkoutUseCase
import com.example.workoutapp.domain.showworkout.UndoSoftDeleteWorkoutUseCase
import com.example.workoutapp.domain.user.GetCurrentUserUseCase
import com.example.workoutapp.domain.user.GetCurrentUserUseCase.Output.ErrorUnauthorized
import com.example.workoutapp.domain.workout.model.WorkoutModel
import com.example.workoutapp.ui.common.BaseViewModel
import com.example.workoutapp.ui.error.ErrorType.Unknown
import com.example.workoutapp.ui.showworkout.adapter.ShowWorkoutItemWrapper
import com.example.workoutapp.ui.showworkout.adapter.ShowWorkoutItemWrapper.WorkoutNoData
import com.example.workoutapp.ui.showworkout.adapter.ShowWorkoutItemWrapper.WorkoutTitle
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.subjects.BehaviorSubject
import javax.inject.Inject
import com.example.workoutapp.domain.showworkout.GetWorkoutUseCase.Output.Success as GetWorkoutSuccess
import com.example.workoutapp.domain.showworkout.SoftDeleteWorkoutUseCase.Output.Success as SoftDeleteWorkouts
import com.example.workoutapp.domain.showworkout.UndoSoftDeleteWorkoutUseCase.Output.Success as UndoSuccess
import com.example.workoutapp.domain.user.GetCurrentUserUseCase.Output.Success as GetUserSuccess

class ShowWorkoutViewModel @Inject constructor(
    private val softDeleteWorkoutUseCase: SoftDeleteWorkoutUseCase,
    private val undoSoftDeleteWorkoutUseCase: UndoSoftDeleteWorkoutUseCase,
    private val getWorkoutUseCase: GetWorkoutUseCase,
    private val getCurrentUserUseCase: GetCurrentUserUseCase
) : BaseViewModel() {

    private var userId: Long = 0

    val getWorkoutList = BehaviorSubject.create<List<ShowWorkoutItemWrapper>>()
    val login = BehaviorSubject.create<Boolean>()
    val deleteWorkoutResponse = BehaviorSubject.create<Long>()
    val showRoutinesResponse = BehaviorSubject.create<Long>()
    val undoOption = BehaviorSubject.create<Long>()
    val undoDelete = BehaviorSubject.create<Boolean>()

    fun getUser() {
        getCurrentUserUseCase.execute(GetCurrentUserUseCase.Input)
            .doOnIoObserveOnMain()
            .subscribeBy {
                when (it) {
                    is GetUserSuccess -> {
                        userId = it.user.id!!
                        getWorkoutsForUser(userId)
                    }
                    is ErrorUnauthorized -> login.onNext(true)
                    else -> error.onNext(Unknown)
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
                        getWorkoutList.onNext(items)
                    }
                    is GetWorkoutSuccess -> {
                        val items = convertToItemWrapper(output.workouts)
                        getWorkoutList.onNext(items)
                    }
                    else -> error.onNext(Unknown)
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

    fun softDeleteWorkout(workoutId: Long) {
        softDeleteWorkoutUseCase.execute(SoftDeleteWorkoutUseCase.Input(workoutId))
            .doOnIoObserveOnMain()
            .subscribeBy {
                when (it) {
                    is SoftDeleteWorkouts -> {
                        getWorkoutsForUser(userId)
                        undoOption.onNext(workoutId)
                    }
                    else -> error.onNext(Unknown)
                }
            }
            .addTo(compositeDisposable)
    }

    fun onUndoDeletion(workoutId: Long) {
        undoSoftDeleteWorkoutUseCase.execute(UndoSoftDeleteWorkoutUseCase.Input(workoutId))
            .doOnIoObserveOnMain()
            .subscribeBy {
                when (it) {
                    is UndoSuccess -> {
                        undoDelete.onNext(true)
                    }
                    else -> error.onNext(Unknown)
                }
            }
            .addTo(compositeDisposable)
    }

    fun onRetryClicked() = getUser()

    fun onWorkoutClicked(workoutId: Long) = showRoutinesResponse.onNext(workoutId)

    fun onDeleteWorkout(workoutId: Long) = deleteWorkoutResponse.onNext(workoutId)


}