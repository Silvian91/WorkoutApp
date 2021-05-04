package com.example.workoutnotebook.ui.editworkout

import com.example.core.ui.BaseViewModel
import com.example.core.ui.error.ErrorType
import com.example.workoutnotebook.domain.addworkout.AddWorkoutUseCase
import com.example.workoutnotebook.domain.extension.doOnIoObserveOnMain
import com.example.workoutnotebook.domain.showroutine.GetTitleUseCase
import com.example.workoutnotebook.domain.showworkout.GetWorkoutUseCase
import com.example.workoutnotebook.domain.user.GetCurrentUserUseCase
import com.example.workoutnotebook.domain.workout.model.WorkoutModel
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.subjects.BehaviorSubject
import javax.inject.Inject

class EditWorkoutViewModel @Inject constructor(
    private val getTitleUseCase: GetTitleUseCase,
    private val addWorkoutUseCase: AddWorkoutUseCase,
    private val getCurrentUserUseCase: GetCurrentUserUseCase,
    private val getWorkoutUseCase: GetWorkoutUseCase
) : BaseViewModel() {

    private var workoutId: Long = 0
    private var userId: Long = 0

    val workoutTitle = BehaviorSubject.create<List<String>>()
    val workoutsListCompare = BehaviorSubject.create<List<String>>()
    val editWorkoutId = BehaviorSubject.create<Long>()
    private val userUnauthorized = BehaviorSubject.create<Boolean>()
    val newWorkoutId = BehaviorSubject.create<Long>()

    fun getUser() {
        getCurrentUserUseCase.execute(GetCurrentUserUseCase.Input)
            .doOnIoObserveOnMain()
            .subscribeBy {
                when (it) {
                    is GetCurrentUserUseCase.Output.Success -> {
                        userId = it.user.id!!
                        getWorkoutsForUser(userId)
                    }
                    is GetCurrentUserUseCase.Output.ErrorUnauthorized -> userUnauthorized.onNext(
                        true
                    )
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
                    is GetWorkoutUseCase.Output.Success -> {
                        val items = convertTitleToStringList(output.workouts)
                        workoutsListCompare.onNext(items)
                    }
                    else -> error.onNext(ErrorType.Unknown)
                }
            }
            .addTo(compositeDisposable)
    }

    fun getTitle() {
        getTitleUseCase.execute(GetTitleUseCase.Input(this.workoutId))
            .doOnIoObserveOnMain()
            .subscribeBy {
                when (it) {
                    is GetTitleUseCase.Output.Success -> {
                        val title = convertTitleToStringList(it.workoutModel)
                        workoutTitle.onNext(title)
                        editWorkoutId.onNext(this.workoutId)
                    }
                    is GetTitleUseCase.Output.ErrorNoTitle -> {
                        error.onNext(ErrorType.Unknown)
                    }
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

    fun setWorkoutId(workoutId: Long) {
        this.workoutId = workoutId
    }

    fun onConfirmClicked(workoutTitle: String) {
        if (workoutTitle.isEmpty()) {
            error.onNext(ErrorType.ErrorWorkoutName)
        } else {
            saveWorkout(WorkoutModel(title = workoutTitle, userId = userId))
        }
    }

    private fun saveWorkout(workoutModel: WorkoutModel) {
        addWorkoutUseCase.execute(AddWorkoutUseCase.Input(workoutModel))
            .doOnIoObserveOnMain()
            .subscribeBy {
                when (it) {
                    is AddWorkoutUseCase.Output.Success -> this.newWorkoutId.onNext(it.workoutId)
                    else -> error.onNext(ErrorType.Unknown)
                }
            }
            .addTo(compositeDisposable)
    }

    fun getEditWorkoutId() = workoutId

}