package com.rosianu.workoutnotebook.ui.profile

import android.content.SharedPreferences
import com.rosianu.workoutnotebook.domain.logout.LogoutUseCase
import com.rosianu.workoutnotebook.domain.profile.GetUserRoutinesUseCase
import com.rosianu.workoutnotebook.domain.showworkout.GetWorkoutUseCase
import com.rosianu.workoutnotebook.domain.user.GetCurrentUserUseCase
import com.rosianu.workoutnotebook.domain.workout.model.WorkoutModel
import com.rosianu.workoutnotebook.ui.common.BaseTest
import io.mockk.mockk
import org.junit.jupiter.api.BeforeEach

class ProfileViewModelTest : BaseTest() {
    private lateinit var viewModel: ProfileViewModel
    private val getCurrentUserUseCase: GetCurrentUserUseCase = mockk()
    private val getWorkoutUseCase: GetWorkoutUseCase = mockk()
    private val getUserRoutinesUseCase: GetUserRoutinesUseCase = mockk()
    private val logoutUseCase: LogoutUseCase = mockk()
    private val sharedPreferences: SharedPreferences = mockk()
    private val workoutModel = listOf( WorkoutModel(2, "Jan 01 - Upper Body", 1) )


    @BeforeEach
    override fun setUp() {
        super.setUp()

        viewModel = ProfileViewModel(
                getCurrentUserUseCase,
                getWorkoutUseCase,
                getUserRoutinesUseCase,
                logoutUseCase,
                sharedPreferences
        )
    }

    //TODO: Implement

}