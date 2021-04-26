package com.example.workoutnotebook.ui.editworkout

import com.example.core.ui.BaseViewModel
import com.example.core.ui.error.ErrorType
import com.example.workoutnotebook.domain.extension.doOnIoObserveOnMain
import com.example.workoutnotebook.domain.showroutine.GetTitleUseCase
import com.example.workoutnotebook.domain.workout.model.WorkoutModel
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.subjects.BehaviorSubject
import javax.inject.Inject

class EditWorkoutViewModel @Inject constructor(
    private val getTitleUseCase: GetTitleUseCase
): BaseViewModel() {

    private var workoutId: Long = 0

    val workoutTitle = BehaviorSubject.create<List<String>>()

    fun getTitle() {
        getTitleUseCase.execute(GetTitleUseCase.Input(workoutId))
            .doOnIoObserveOnMain()
            .subscribeBy {
                when (it) {
                    is GetTitleUseCase.Output.Success -> {
                        val title = convertTitleToStringList(it.workoutModel)
                        workoutTitle.onNext(title)
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

}