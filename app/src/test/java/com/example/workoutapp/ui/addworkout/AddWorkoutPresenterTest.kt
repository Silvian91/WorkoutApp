package com.example.workoutapp.ui.addworkout

import com.example.workoutapp.data.database.workout.WorkoutEntity
import com.example.workoutapp.data.database.workout.WorkoutRepository
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import io.reactivex.Single
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class AddWorkoutPresenterTest {

    private val view: AddWorkoutContract.View = mockk(relaxed = true)
    private val repository: WorkoutRepository = mockk()
    private val compositeDisposable: CompositeDisposable = mockk(relaxed = true)
    private val workoutTitleField: String = "test string"


    private val presenter = AddWorkoutPresenter(repository, compositeDisposable)
    private val workoutEntity =
        WorkoutEntity(workoutTitleField)

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
    fun finishClearsCompositeDisposable() {
        //setup
        //nothing to do here

        //execution
        presenter.finish()

        //assertion
        verify(exactly = 1) { compositeDisposable.clear() }
    }

    @Test
    fun testSavingOfWorkoutTitle() {
        //input
        every { repository.insertWorkout(workoutEntity) } returns Single.just(6789839)
        presenter.setView(view)

        //execution
        presenter.onConfirmClicked(workoutTitleField)

        //expected output
        verify(exactly = 1) { repository.insertWorkout(workoutEntity) }
        verify(exactly = 1) { view.showAddRoutine(6789839) }
        verify(exactly = 0) { view.showError() }
    }

}