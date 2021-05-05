package com.rosianu.workoutnotebook.ui.copyworkout

import com.rosianu.core.ui.BaseViewModel
import com.rosianu.core.ui.error.ErrorType
import com.rosianu.workoutnotebook.domain.extension.doOnIoObserveOnMain
import com.rosianu.workoutnotebook.domain.showworkout.GetWorkoutUseCase
import com.rosianu.workoutnotebook.domain.showworkout.GetWorkoutUseCase.Output.Success
import com.rosianu.workoutnotebook.domain.showworkout.GetWorkoutUseCase.Output.SuccessNoData
import com.rosianu.workoutnotebook.domain.user.GetCurrentUserUseCase
import com.rosianu.workoutnotebook.domain.workout.model.WorkoutModel
import com.rosianu.workoutnotebook.ui.copyworkout.adapter.WorkoutItemWrapper
import com.rosianu.workoutnotebook.ui.copyworkout.adapter.WorkoutItemWrapper.WorkoutNoData
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.subjects.BehaviorSubject
import javax.inject.Inject

class CopyWorkoutViewModel @Inject constructor(
    private val getWorkoutUseCase: GetWorkoutUseCase,
    private val getCurrentUserUseCase: GetCurrentUserUseCase
) : BaseViewModel() {

    private var userId: Long = 0
    private var selectedItem: WorkoutModel? = null
    private var workoutId: Long = 0

    val getWorkoutList = BehaviorSubject.create<List<WorkoutItemWrapper>>()
    val login = BehaviorSubject.create<Boolean>()
    val editWorkoutResponse = BehaviorSubject.create<Long>()

    fun getUser() {
        getCurrentUserUseCase.execute(GetCurrentUserUseCase.Input)
            .doOnIoObserveOnMain()
            .subscribeBy {
                when (it) {
                    is GetCurrentUserUseCase.Output.Success -> {
                        userId = it.user.id!!
                        getWorkoutsForUser(userId)
                    }
                    is GetCurrentUserUseCase.Output.ErrorUnauthorized -> login.onNext(true)
                    else -> error.onNext(ErrorType.Unknown)
                }
            }
            .addTo(compositeDisposable)
    }

    fun getWorkoutsForUser(userId: Long) {
        getWorkoutUseCase.execute(GetWorkoutUseCase.Input(userId))
            .doOnIoObserveOnMain()
            .subscribeBy { output ->
                when (output) {
                    is SuccessNoData -> {
                        val items = convertToItemWrapper()
                        getWorkoutList.onNext(items)
                    }
                    is Success -> {
                        val items = convertToItemWrapper(output.workouts)
                        getWorkoutList.onNext(items)
                    }
                    else -> error.onNext(ErrorType.Unknown)
                }
            }
            .addTo(compositeDisposable)
    }

    private fun convertToItemWrapper(models: List<WorkoutModel> = emptyList()): List<WorkoutItemWrapper> {
        val itemWrappers = ArrayList<WorkoutItemWrapper>()
        if (models.isNotEmpty()) {
            models.map {
                itemWrappers.add(
                    WorkoutItemWrapper.WorkoutTitle(it, it == selectedItem)
                    { onCheckedChange(it) }
                )
            }
        } else {
            itemWrappers.add(WorkoutNoData)
        }
        return itemWrappers
    }

    private fun onCheckedChange(workoutModel: WorkoutModel) {
        selectedItem = workoutModel
        workoutId = workoutModel.id!!
        getWorkoutsForUser(userId)
    }

    fun workoutResponse() {
        editWorkoutResponse.onNext(workoutId)
    }

}