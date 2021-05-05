package com.rosianu.workoutnotebook.ui.addworkout

import com.rosianu.core.ui.BaseViewModel
import com.rosianu.core.ui.error.ErrorType.ErrorWorkoutName
import com.rosianu.core.ui.error.ErrorType.Unknown
import com.rosianu.workoutnotebook.domain.addworkout.AddWorkoutUseCase
import com.rosianu.workoutnotebook.domain.addworkout.AddWorkoutUseCase.Input
import com.rosianu.workoutnotebook.domain.extension.doOnIoObserveOnMain
import com.rosianu.workoutnotebook.domain.showworkout.GetWorkoutUseCase
import com.rosianu.workoutnotebook.domain.user.GetCurrentUserUseCase
import com.rosianu.workoutnotebook.domain.user.GetCurrentUserUseCase.Output.ErrorUnauthorized
import com.rosianu.workoutnotebook.domain.workout.model.WorkoutModel
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.subjects.BehaviorSubject
import javax.inject.Inject
import com.rosianu.workoutnotebook.domain.addworkout.AddWorkoutUseCase.Output.Success as SaveWorkoutSuccess
import com.rosianu.workoutnotebook.domain.user.GetCurrentUserUseCase.Output.Success as GetUserSuccess

class AddWorkoutViewModel @Inject constructor(
    private val addWorkoutUseCase: AddWorkoutUseCase,
    private val getCurrentUserUseCase: GetCurrentUserUseCase,
    private val getWorkoutUseCase: GetWorkoutUseCase
) : BaseViewModel() {

    private var userId: Long = 0

    val userUnauthorized = BehaviorSubject.create<Boolean>()
    val workout = BehaviorSubject.create<Long>()
    val workoutsListCompare = BehaviorSubject.create<List<String>>()

    fun getUser() {
        getCurrentUserUseCase.execute(GetCurrentUserUseCase.Input)
            .doOnIoObserveOnMain()
            .subscribeBy {
                when (it) {
                    is GetUserSuccess -> {
                        userId = it.user.id!!
                        getWorkoutsForUser(userId)
                    }
                    is ErrorUnauthorized -> userUnauthorized.onNext(true)
                    else -> error.onNext(Unknown)
                }
            }
            .addTo(compositeDisposable)
    }

    private fun getWorkoutsForUser(userId: Long) {
        getWorkoutUseCase.execute(GetWorkoutUseCase.Input(userId))
            .doOnIoObserveOnMain()
            .subscribeBy { output ->
                when (output) {
                    is GetWorkoutUseCase.Output.Success -> {
                        val items = convertTitleToStringList(output.workouts)
                        workoutsListCompare.onNext(items)
                    }
                    else -> error.onNext(Unknown)
                }
            }
            .addTo(compositeDisposable)
    }

    private fun convertTitleToStringList(models: List<WorkoutModel>): List<String> {
        val titles = ArrayList<String>()
        models.forEach {
            titles.add(it.title)
        }
        return titles
    }

    fun onConfirmClicked(workoutTitle: String) {
        if (workoutTitle.isEmpty()) {
            error.onNext(ErrorWorkoutName)
        } else {
            saveWorkout(WorkoutModel(title = workoutTitle, userId = userId))
        }
    }

    fun saveWorkout(workoutModel: WorkoutModel) {
        addWorkoutUseCase.execute(Input(workoutModel))
            .doOnIoObserveOnMain()
            .subscribeBy {
                when (it) {
                    is SaveWorkoutSuccess -> workout.onNext(it.workoutId)
                    else -> error.onNext(Unknown)
                }
            }
            .addTo(compositeDisposable)
    }

}