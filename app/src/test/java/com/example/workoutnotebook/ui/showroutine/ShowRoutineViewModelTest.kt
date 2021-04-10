package com.example.workoutnotebook.ui.showroutine

import com.example.workoutnotebook.domain.routine.model.RoutineModel
import com.example.workoutnotebook.domain.showroutine.DeleteWorkoutUseCase
import com.example.workoutnotebook.domain.showroutine.GetRoutineUseCase
import com.example.workoutnotebook.domain.showroutine.GetTitleUseCase
import com.example.workoutnotebook.domain.workout.model.WorkoutModel
import com.example.workoutnotebook.ui.common.BaseTest
import io.mockk.every
import io.mockk.mockk
import io.reactivex.Single
import org.junit.jupiter.api.BeforeEach

import org.junit.jupiter.api.Test

class ShowRoutineViewModelTest:BaseTest() {
    private lateinit var viewModel: ShowRoutineViewModel
    private val deleteWorkoutUseCase: DeleteWorkoutUseCase = mockk()
    private val getRoutineUseCase: GetRoutineUseCase = mockk()
    private val getTitleUseCase: GetTitleUseCase = mockk()
    private val workoutModel = listOf(
            WorkoutModel(1, "Jan 03 - Upper Body", 3)
    )
    private val routineModel = listOf(
            RoutineModel("Bench Press", "3", "4", "15.5", "2 minutes", 1, 1),
            RoutineModel("Squat", "3", "4", "17.5", "3 minutes", 1, 1)
    )

    @BeforeEach
    override fun setUp() {
        super.setUp()

        viewModel = ShowRoutineViewModel(
            deleteWorkoutUseCase,
            getRoutineUseCase,
            getTitleUseCase
        )
    }

    // TODO: Fix test, it only passes when Input(0)
    @Test
    fun `get title emits output success`(){
        every { getTitleUseCase.execute(GetTitleUseCase.Input(1)) } returns
                Single.just(GetTitleUseCase.Output.Success(workoutModel))

        viewModel.getTitle()

        getTitleUseCase.execute(GetTitleUseCase.Input(1)).test()
                .assertValue(GetTitleUseCase.Output.Success(workoutModel))
    }

    // TODO: Fix test, it only passes when Input(0)
    @Test
    fun `get title emits output error no title`(){
        every { getTitleUseCase.execute(GetTitleUseCase.Input(1)) } returns
                Single.just(GetTitleUseCase.Output.ErrorNoTitle)

        viewModel.getTitle()

        getTitleUseCase.execute(GetTitleUseCase.Input(1)).test()
                .assertValue(GetTitleUseCase.Output.ErrorNoTitle)
    }

    // TODO: Fix test, it only passes when Input(0)
    @Test
    fun `get routines emits output success`(){
        every { getRoutineUseCase.execute(GetRoutineUseCase.Input(1)) } returns
                Single.just(GetRoutineUseCase.Output.Success(routineModel))

        viewModel.getRoutine()

        getRoutineUseCase.execute(GetRoutineUseCase.Input(1)).test()
                .assertValue(GetRoutineUseCase.Output.Success(routineModel))
    }

    // TODO: Fix test, it only passes when Input(0)
    @Test
    fun `get routines emits output error no routines`(){
        every { getRoutineUseCase.execute(GetRoutineUseCase.Input(1)) } returns
                Single.just(GetRoutineUseCase.Output.ErrorNoRoutines)

        viewModel.getRoutine()

        getRoutineUseCase.execute(GetRoutineUseCase.Input(1)).test()
                .assertValue(GetRoutineUseCase.Output.ErrorNoRoutines)
    }

    @Test
    fun `on delete workout emits output success`(){
        every { deleteWorkoutUseCase.execute(DeleteWorkoutUseCase.Input(1)) } returns
                Single.just(DeleteWorkoutUseCase.Output.Success)

        viewModel.onDeleteConfirmed(1)

        deleteWorkoutUseCase.execute(DeleteWorkoutUseCase.Input(1)).test()
                .assertValue(DeleteWorkoutUseCase.Output.Success)
    }

    @Test
    fun `on delete workout emits output error not deleted`(){
        every { deleteWorkoutUseCase.execute(DeleteWorkoutUseCase.Input(1)) } returns
                Single.just(DeleteWorkoutUseCase.Output.ErrorNotDeleted)

        viewModel.onDeleteConfirmed(1)

        deleteWorkoutUseCase.execute(DeleteWorkoutUseCase.Input(1)).test()
                .assertValue(DeleteWorkoutUseCase.Output.ErrorNotDeleted)
    }

}