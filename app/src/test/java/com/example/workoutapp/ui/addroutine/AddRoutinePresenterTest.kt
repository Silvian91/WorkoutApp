package com.example.workoutapp.ui.addroutine

import com.example.workoutapp.ui.addroutine.AddRoutineContract.ErrorType.*
import com.example.workoutapp.data.database.routine.RoutineRepository
import com.example.workoutapp.data.database.workout.WorkoutRepository
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
    fun showErrorForEmptyName() {
        val routineName = ""
        val routineSets = "sets"
        val routineReps = "reps"
        val routineWeight = "weight"
        val routineWeightMeasurement = "kg"
        val routineRest = "rest"
        presenter.setView(view)

        presenter.onContinueClicked(
            routineName,
            routineSets,
            routineReps,
            routineWeight,
            routineWeightMeasurement,
            routineRest
        )

        verify(exactly = 1) { view.showError(NAME_EMPTY) }
        verify(exactly = 0) { view.clearAllInputFields() }
        verify(exactly = 0) { view.resetFocus() }
        verify(exactly = 0) { view.showError(SETS_EMPTY) }
        verify(exactly = 0) { view.showError(REST_EMPTY) }
        verify(exactly = 0) { view.showError(WEIGHT_MEASUREMENT_EMPTY) }
    }

    @Test
    fun showErrorForEmptySets() {
        val routineName = "name"
        val routineSets = ""
        val routineReps = "reps"
        val routineWeight = "weight"
        val routineWeightMeasurement = "kg"
        val routineRest = "rest"
        presenter.setView(view)

        presenter.onContinueClicked(
            routineName,
            routineSets,
            routineReps,
            routineWeight,
            routineWeightMeasurement,
            routineRest
        )

        verify(exactly = 1) { view.showError(SETS_EMPTY) }
        verify(exactly = 0) { view.clearAllInputFields() }
        verify(exactly = 0) { view.resetFocus() }
        verify(exactly = 0) { view.showError(NAME_EMPTY) }
        verify(exactly = 0) { view.showError(REST_EMPTY) }
    }

    @Test
    fun showErrorForEmptyReps() {
        val routineName = "name"
        val routineSets = "sets"
        val routineReps = ""
        val routineWeight = "weight"
        val routineWeightMeasurement = "kg"
        val routineRest = "rest"
        presenter.setView(view)

        presenter.onContinueClicked(
            routineName,
            routineSets,
            routineReps,
            routineWeight,
            routineWeightMeasurement,
            routineRest
        )

        verify(exactly = 1) { view.showError(REPS_EMPTY) }
        verify(exactly = 0) { view.clearAllInputFields() }
        verify(exactly = 0) { view.resetFocus() }
        verify(exactly = 0) { view.showError(SETS_EMPTY) }
        verify(exactly = 0) { view.showError(WEIGHT_EMPTY) }
    }

    @Test
    fun showErrorForEmptyWeight() {
        val routineName = "name"
        val routineSets = "sets"
        val routineReps = "reps"
        val routineWeight = ""
        val routineWeightMeasurement = "kg"
        val routineRest = "rest"
        presenter.setView(view)

        presenter.onContinueClicked(
            routineName,
            routineSets,
            routineReps,
            routineWeight,
            routineWeightMeasurement,
            routineRest
        )

        verify(exactly = 1) { view.showError(WEIGHT_EMPTY) }
        verify(exactly = 0) { view.clearAllInputFields() }
        verify(exactly = 0) { view.resetFocus() }
        verify(exactly = 0) { view.showError(NAME_EMPTY) }
        verify(exactly = 0) { view.showError(REPS_EMPTY) }
    }

    @Test
    fun showErrorForEmptyWeightMeasurement() {
        val routineName = "name"
        val routineSets = "sets"
        val routineReps = "reps"
        val routineWeight = "1"
        val routineWeightMeasurement = ""
        val routineRest = "rest"
        presenter.setView(view)

        presenter.onContinueClicked(
            routineName,
            routineSets,
            routineReps,
            routineWeight,
            routineWeightMeasurement,
            routineRest
        )

        verify(exactly = 1) { view.showError(WEIGHT_MEASUREMENT_EMPTY) }
        verify(exactly = 0) { view.clearAllInputFields() }
        verify(exactly = 0) { view.resetFocus() }
        verify(exactly = 0) { view.showError(WEIGHT_EMPTY) }
        verify(exactly = 0) { view.showError(NAME_EMPTY) }
        verify(exactly = 0) { view.showError(REPS_EMPTY) }
    }

    @Test
    fun showErrorForEmptyRest() {
        val routineName = "name"
        val routineSets = "sets"
        val routineReps = "reps"
        val routineWeight = "weight"
        val routineWeightMeasurement = "kg"
        val routineRest = ""
        presenter.setView(view)

        presenter.onContinueClicked(
            routineName,
            routineSets,
            routineReps,
            routineWeight,
            routineWeightMeasurement,
            routineRest
        )

        verify(exactly = 1) { view.showError(REST_EMPTY) }
        verify(exactly = 0) { view.clearAllInputFields() }
        verify(exactly = 0) { view.resetFocus() }
        verify(exactly = 0) { view.showError(SETS_EMPTY) }
        verify(exactly = 0) { view.showError(NAME_EMPTY) }
    }

    @Test
    fun showErrorForEmptyNameSets() {
        val routineName = ""
        val routineSets = ""
        val routineReps = "reps"
        val routineWeight = "weight"
        val routineWeightMeasurement = "kg"
        val routineRest = "rest"
        presenter.setView(view)

        presenter.onContinueClicked(
            routineName,
            routineSets,
            routineReps,
            routineWeight,
            routineWeightMeasurement,
            routineRest
        )

        verify(exactly = 1) { view.showError(NAME_EMPTY) }
        verify(exactly = 0) { view.clearAllInputFields() }
        verify(exactly = 0) { view.resetFocus() }
        verify(exactly = 0) { view.showError(SETS_EMPTY) }
        verify(exactly = 0) { view.showError(WEIGHT_EMPTY) }
        verify(exactly = 0) { view.showError(REST_EMPTY) }
    }

    @Test
    fun showErrorForEmptySetsReps() {
        val routineName = "name"
        val routineSets = ""
        val routineReps = ""
        val routineWeight = "weight"
        val routineWeightMeasurement = "kg"
        val routineRest = "rest"
        presenter.setView(view)

        presenter.onContinueClicked(
            routineName,
            routineSets,
            routineReps,
            routineWeight,
            routineWeightMeasurement,
            routineRest
        )

        verify(exactly = 1) { view.showError(SETS_EMPTY) }
        verify(exactly = 0) { view.clearAllInputFields() }
        verify(exactly = 0) { view.resetFocus() }
        verify(exactly = 0) { view.showError(REPS_EMPTY) }
        verify(exactly = 0) { view.showError(WEIGHT_EMPTY) }
        verify(exactly = 0) { view.showError(REST_EMPTY) }
    }

    @Test
    fun showErrorForEmptyRepsRest() {
        val routineName = "name"
        val routineSets = "sets"
        val routineReps = ""
        val routineWeight = "weight"
        val routineWeightMeasurement = "kg"
        val routineRest = ""
        presenter.setView(view)

        presenter.onContinueClicked(
            routineName,
            routineSets,
            routineReps,
            routineWeight,
            routineWeightMeasurement,
            routineRest
        )

        verify(exactly = 1) { view.showError(REPS_EMPTY) }
        verify(exactly = 0) { view.clearAllInputFields() }
        verify(exactly = 0) { view.resetFocus() }
        verify(exactly = 0) { view.showError(NAME_EMPTY) }
        verify(exactly = 0) { view.showError(SETS_EMPTY) }
        verify(exactly = 0) { view.showError(WEIGHT_EMPTY) }
    }

    @Test
    fun showErrorForEmptyNameSetsReps() {
        val routineName = ""
        val routineSets = ""
        val routineReps = ""
        val routineWeight = "weight"
        val routineWeightMeasurement = "kg"
        val routineRest = "rest"
        presenter.setView(view)

        presenter.onContinueClicked(
            routineName,
            routineSets,
            routineReps,
            routineWeight,
            routineWeightMeasurement,
            routineRest
        )

        verify(exactly = 1) { view.showError(NAME_EMPTY) }
        verify(exactly = 0) { view.clearAllInputFields() }
        verify(exactly = 0) { view.resetFocus() }
        verify(exactly = 0) { view.showError(REPS_EMPTY) }
        verify(exactly = 0) { view.showError(WEIGHT_EMPTY) }
        verify(exactly = 0) { view.showError(REST_EMPTY) }
    }

    @Test
    fun showErrorForEmptyNameSetsRepsWeight() {
        val routineName = ""
        val routineSets = ""
        val routineReps = ""
        val routineWeight = ""
        val routineWeightMeasurement = "kg"
        val routineRest = "rest"
        presenter.setView(view)

        presenter.onContinueClicked(
            routineName,
            routineSets,
            routineReps,
            routineWeight,
            routineWeightMeasurement,
            routineRest
        )

        verify(exactly = 1) { view.showError(NAME_EMPTY) }
        verify(exactly = 0) { view.clearAllInputFields() }
        verify(exactly = 0) { view.resetFocus() }
        verify(exactly = 0) { view.showError(REPS_EMPTY) }
        verify(exactly = 0) { view.showError(WEIGHT_EMPTY) }
        verify(exactly = 0) { view.showError(REST_EMPTY) }
    }

    @Test
    fun showErrorForEmptySetsRepsWeight() {
        val routineName = "name"
        val routineSets = ""
        val routineReps = ""
        val routineWeight = ""
        val routineWeightMeasurement = "kg"
        val routineRest = "rest"
        presenter.setView(view)

        presenter.onContinueClicked(
            routineName,
            routineSets,
            routineReps,
            routineWeight,
            routineWeightMeasurement,
            routineRest
        )

        verify(exactly = 1) { view.showError(SETS_EMPTY) }
        verify(exactly = 0) { view.clearAllInputFields() }
        verify(exactly = 0) { view.resetFocus() }
        verify(exactly = 0) { view.showError(NAME_EMPTY) }
        verify(exactly = 0) { view.showError(WEIGHT_EMPTY) }
        verify(exactly = 0) { view.showError(REST_EMPTY) }
    }

    @Test
    fun showErrorForEmptySetsRepsWeightRest() {
        val routineName = "name"
        val routineSets = ""
        val routineReps = ""
        val routineWeight = ""
        val routineWeightMeasurement = "kg"
        val routineRest = ""
        presenter.setView(view)

        presenter.onContinueClicked(
            routineName,
            routineSets,
            routineReps,
            routineWeight,
            routineWeightMeasurement,
            routineRest
        )

        verify(exactly = 1) { view.showError(SETS_EMPTY) }
        verify(exactly = 0) { view.clearAllInputFields() }
        verify(exactly = 0) { view.resetFocus() }
        verify(exactly = 0) { view.showError(NAME_EMPTY) }
        verify(exactly = 0) { view.showError(REPS_EMPTY) }
        verify(exactly = 0) { view.showError(WEIGHT_EMPTY) }
        verify(exactly = 0) { view.showError(REST_EMPTY) }
    }

    @Test
    fun showErrorForEmptyNameSetsRest() {
        val routineName = ""
        val routineSets = ""
        val routineReps = "reps"
        val routineWeight = "weight"
        val routineWeightMeasurement = "kg"
        val routineRest = ""
        presenter.setView(view)

        presenter.onContinueClicked(
            routineName,
            routineSets,
            routineReps,
            routineWeight,
            routineWeightMeasurement,
            routineRest
        )

        verify(exactly = 1) { view.showError(NAME_EMPTY) }
        verify(exactly = 0) { view.clearAllInputFields() }
        verify(exactly = 0) { view.resetFocus() }
        verify(exactly = 0) { view.showError(SETS_EMPTY) }
        verify(exactly = 0) { view.showError(REPS_EMPTY) }
        verify(exactly = 0) { view.showError(WEIGHT_EMPTY) }
        verify(exactly = 0) { view.showError(REST_EMPTY) }
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