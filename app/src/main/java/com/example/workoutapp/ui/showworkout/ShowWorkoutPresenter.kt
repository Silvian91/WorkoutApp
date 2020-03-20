package com.example.workoutapp.ui.showworkout

import com.example.workoutapp.domain.workout.WorkoutRepository
import com.example.workoutapp.domain.workout.model.WorkoutModel
import com.example.workoutapp.ui.showworkout.adapter.ShowWorkoutItemWrapper
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers

class ShowWorkoutPresenter(
    private val workoutRepository: WorkoutRepository,
    private val compositeDisposable: CompositeDisposable
) : ShowWorkoutContract.Presenter {

    private lateinit var view: ShowWorkoutContract.View

    override fun setView(view: ShowWorkoutContract.View) {
        this.view = view
    }

    override fun start() {
         workoutRepository.getAllWorkouts()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map { convertToItemWrapper(it) }
            .subscribe { workouts -> view.showWorkoutListData(workouts) }
            .addTo(compositeDisposable)
    }

    private fun convertToItemWrapper(models: ArrayList<WorkoutModel>): List<ShowWorkoutItemWrapper> {
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