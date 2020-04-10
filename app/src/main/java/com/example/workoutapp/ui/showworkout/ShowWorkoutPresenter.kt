package com.example.workoutapp.ui.showworkout

import com.example.workoutapp.domain.extension.doOnIoObserveOnMain
import com.example.workoutapp.domain.showworkout.GetWorkoutsUseCase
import com.example.workoutapp.domain.workout.model.WorkoutModel
import com.example.workoutapp.ui.showworkout.adapter.ShowWorkoutItemWrapper
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
                    is GetWorkoutsUseCase.Output.SuccessNoData -> view.showEmptyScreen()
                    is GetWorkoutsUseCase.Output.Success -> {
                        val items = convertToItemWrapper(it.workouts)
                        view.showWorkoutListData(items)
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

    private fun convertToItemWrapper(models: List<WorkoutModel>): List<ShowWorkoutItemWrapper> {
        val itemWrappers = ArrayList<ShowWorkoutItemWrapper>()
        models.forEach {
            itemWrappers.add(ShowWorkoutItemWrapper.WorkoutTitle(it))
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