package com.example.workoutapp.addroutine

import com.example.workoutapp.model.routine.RoutineEntity
import com.example.workoutapp.model.routine.RoutineRepository
import com.example.workoutapp.model.workout.WorkoutRepository
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class AddRoutinePresenterTest {

    private val view: AddRoutineContract.View = mockk(relaxed = true)
    private val routineRepository: RoutineRepository = mockk()
    private val workoutRepository: WorkoutRepository = mockk()
    private val compositeDisposable: CompositeDisposable = mockk(relaxed = true)

    private val presenter =
        AddRoutinePresenter(routineRepository, workoutRepository, compositeDisposable)


    private val routineName: String = "name"
    private val routineReps: String = "set"
    private val routineSets: String = "reps"
    private val routineWeight: String = "weight"
    private val routineRest: String = "rest"
    private val routineEmptyString: String = ""
    private val workoutId: Long = 0
    private val routineEntity =
        RoutineEntity(routineName, routineReps, routineSets, routineWeight, routineRest, workoutId)
    private val routinePairs = ArrayList<RoutineEntity>()


    @BeforeEach
    fun setUp() {
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
        RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }
    }

    @AfterEach
    fun cleanup() {
        RxAndroidPlugins.reset()
        RxJavaPlugins.reset()
    }

    @Test
    fun testShowError(
        routine_name: String, routine_sets: String, routine_reps: String,
        routine_weight: String, routine_rest: String
    ) {
        every { routinePairs.add(routineEntity) } returns true

        presenter.setView(view)

        presenter.onContinueClicked(
            routine_name, routine_sets,
            routine_reps, routine_weight, routine_rest
        )


        verify { routinePairs.add(routineEntity) }
    }

    @Test
    fun finishClearsCompositeDisposable() {
        //setup
        //nothing to do here

        //execution
        presenter.finish()

        //assertion
        verify(exactly = 1) { compositeDisposable.clear() }
    }

}