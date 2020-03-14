package com.example.workoutapp.ui.showroutine

import com.example.workoutapp.data.routine.RoutineRepository
import com.example.workoutapp.data.workout.WorkoutRepository
import com.example.workoutapp.domain.routine.model.RoutineModel
import com.example.workoutapp.ui.showroutine.adapter.ShowRoutineItemWrapper
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers

class ShowRoutinePresenter(
    private val routineRepository: RoutineRepository,
    private val workoutRepository: WorkoutRepository,
    private val compositeDisposable: CompositeDisposable
) : ShowRoutineContract.Presenter {

    private lateinit var view: ShowRoutineContract.View

    private var workoutId: Long = 0

    override fun start() {
        routineRepository.getRoutine(workoutId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map {
                val models = ArrayList<RoutineModel>()
                it.forEach { entity ->
                    models.add(entity.toModel())
                }
                models
            }
            .map {
                convertToItemWrappers(it)
            }
            .subscribe { routineData ->
                view.showRoutineData(routineData)
            }
            .addTo(compositeDisposable)
    }

    private fun convertToItemWrappers(models: ArrayList<RoutineModel>): List<ShowRoutineItemWrapper> {
        val itemWrappers = ArrayList<ShowRoutineItemWrapper>()
        itemWrappers.add(ShowRoutineItemWrapper.Title("Routines"))

        models.forEach {
            itemWrappers.add(ShowRoutineItemWrapper.Entry(it))
        }

        return itemWrappers
    }

    override fun finish() {
        compositeDisposable.clear()
    }

    override fun setView(view: ShowRoutineContract.View) {
        this.view = view
    }

    override fun setWorkoutId(workoutId: Long) {
        this.workoutId = workoutId
    }

    override fun onDeleteClicked(workoutId: Long) {
        workoutRepository.deleteRoutine(workoutId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { finish() }
            .addTo(compositeDisposable)
        view.nextActivity()
    }

}