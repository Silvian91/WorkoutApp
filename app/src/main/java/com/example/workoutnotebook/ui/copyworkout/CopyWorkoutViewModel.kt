package com.example.workoutnotebook.ui.copyworkout

import com.example.core.ui.BaseViewModel
import com.example.core.ui.error.ErrorType
import com.example.workoutnotebook.domain.extension.doOnIoObserveOnMain
import com.example.workoutnotebook.domain.showworkout.GetWorkoutUseCase
import com.example.workoutnotebook.domain.user.GetCurrentUserUseCase
import com.example.workoutnotebook.domain.workout.model.WorkoutModel
import com.example.workoutnotebook.ui.copyworkout.adapter.WorkoutItemWrapper
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

    val getWorkoutList = BehaviorSubject.create<List<WorkoutItemWrapper>>()
    val login = BehaviorSubject.create<Boolean>()
    val showRoutinesResponse = BehaviorSubject.create<Long>()

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
                    is GetWorkoutUseCase.Output.Success -> {
                        val items = convertToItemWrapper(output.workouts)
                        getWorkoutList.onNext(items)
                    }
                    else -> error.onNext(ErrorType.Unknown)
                }
            }
            .addTo(compositeDisposable)
    }

    fun routinesResponse(workoutId: Long){
        showRoutinesResponse.onNext(workoutId)
    }

    private fun convertToItemWrapper(models: List<WorkoutModel> = emptyList()): List<WorkoutItemWrapper> {
        return models.map {
            WorkoutItemWrapper.WorkoutTitle(it, it == selectedItem) {
                onCheckedChange(it)
            }
        }
    }

    private fun onCheckedChange(workoutModel: WorkoutModel) {
        selectedItem = workoutModel
        getWorkoutsForUser(userId)
    }

}