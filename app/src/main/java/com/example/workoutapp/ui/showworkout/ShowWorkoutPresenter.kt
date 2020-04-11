package com.example.workoutapp.ui.showworkout

import com.example.workoutapp.domain.extension.doOnIoObserveOnMain
import com.example.workoutapp.domain.showworkout.GetWorkoutsUseCase
import com.example.workoutapp.domain.workout.model.WorkoutModel
import com.example.workoutapp.ui.showworkout.adapter.ShowWorkoutItemWrapper.WorkoutNoData
import com.example.workoutapp.ui.showworkout.adapter.ShowWorkoutItemWrapper.WorkoutTitle
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy

class ShowWorkoutPresenter(
    private val workoutsUseCase: GetWorkoutsUseCase,
    private val compositeDisposable: CompositeDisposable
) : ShowWorkoutContract.Presenter {

    private lateinit var view: ShowWorkoutContract.View

    override fun setView(view: ShowWorkoutContract.View) {
        this.view = view
    }

    override fun start() {
        workoutsUseCase.execute(GetWorkoutsUseCase.Input)
            .doOnIoObserveOnMain()
            .subscribeBy {
                when (it) {
                    is GetWorkoutsUseCase.Output.SuccessNoData -> {
                        val items = convertToItemWrapperNoData(it.noWorkouts)
                        view.showNoWorkoutsListData(items)
                    }
                    is GetWorkoutsUseCase.Output.Success -> {
                        val items = convertToItemWrapper(it.workouts)
                        view.showWorkoutsListData(items)
                    }
                    else -> view.showError()
                }
            }
            .addTo(compositeDisposable)
//         workoutRepository.getAllWorkouts()
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .map { convertToItemWrapper(it) }
//            .subscribe { workouts -> view.showWorkoutListData(workouts) }
//            .addTo(compositeDisposable)
    }

    private fun convertToItemWrapper(models: List<WorkoutModel>): List<WorkoutTitle> {
        val itemWrappers = ArrayList<WorkoutTitle>()
        models.forEach {
            itemWrappers.add(WorkoutTitle(it))
        }
        return itemWrappers
    }

    private fun convertToItemWrapperNoData(models: List<WorkoutModel>): List<WorkoutNoData> {
        val itemWrappers = ArrayList<WorkoutNoData>()
        models.forEach {
            itemWrappers.add(WorkoutNoData(it))
        }
        return itemWrappers
    }

    override fun finish() {
        compositeDisposable.clear()
    }

    override fun onWorkoutClicked(workoutId: Long) {
        view.showRoutines(workoutId)
    }

}