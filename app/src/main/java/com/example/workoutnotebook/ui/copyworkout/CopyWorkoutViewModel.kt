package com.example.workoutnotebook.ui.copyworkout

import com.example.core.ui.BaseViewModel
import com.example.core.ui.error.ErrorType
import com.example.workoutnotebook.domain.extension.doOnIoObserveOnMain
import com.example.workoutnotebook.domain.showworkout.GetWorkoutUseCase
import com.example.workoutnotebook.domain.showworkout.SoftDeleteWorkoutUseCase
import com.example.workoutnotebook.domain.showworkout.UndoSoftDeleteWorkoutUseCase
import com.example.workoutnotebook.domain.user.GetCurrentUserUseCase
import com.example.workoutnotebook.domain.workout.model.WorkoutModel
import com.example.workoutnotebook.ui.showworkout.adapter.ShowWorkoutItemWrapper
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.subjects.BehaviorSubject
import javax.inject.Inject

class CopyWorkoutViewModel @Inject constructor(
    private val getWorkoutUseCase: GetWorkoutUseCase,
    private val getCurrentUserUseCase: GetCurrentUserUseCase
) : BaseViewModel() {

    private var userId: Long = 0

    val getWorkoutList = BehaviorSubject.create<List<ShowWorkoutItemWrapper>>()
    val login = BehaviorSubject.create<Boolean>()

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

    private fun getWorkoutsForUser(userId: Long) {
        getWorkoutUseCase.execute(GetWorkoutUseCase.Input(userId))
            .doOnIoObserveOnMain()
            .subscribeBy { output ->
                when (output) {
                    is GetWorkoutUseCase.Output.SuccessNoData -> {
                        val items = convertToItemWrapper()
                        getWorkoutList.onNext(items)
                    }
                    is GetWorkoutUseCase.Output.Success -> {
                        val items = convertToItemWrapper(output.workouts)
                        getWorkoutList.onNext(items)
                    }
                    else -> error.onNext(ErrorType.Unknown)
                }
            }
            .addTo(compositeDisposable)
    }

    private fun convertToItemWrapper(models: List<WorkoutModel> = emptyList()): List<ShowWorkoutItemWrapper> {
        val itemWrappers = ArrayList<ShowWorkoutItemWrapper>()
        if (models.isNotEmpty()) {
            models.forEach {
                itemWrappers.add(ShowWorkoutItemWrapper.WorkoutTitle(it))
            }
        } else {
            itemWrappers.add(ShowWorkoutItemWrapper.WorkoutNoData)
        }
        return itemWrappers
    }

}