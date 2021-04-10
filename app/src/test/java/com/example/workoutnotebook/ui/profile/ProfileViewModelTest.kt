package com.example.workoutnotebook.ui.profile

import android.content.SharedPreferences
import com.example.workoutnotebook.domain.logout.LogoutUseCase
import com.example.workoutnotebook.domain.profile.GetUserRoutinesUseCase
import com.example.workoutnotebook.domain.showworkout.GetWorkoutUseCase
import com.example.workoutnotebook.domain.user.GetCurrentUserUseCase
import com.example.workoutnotebook.domain.workout.model.WorkoutModel
import com.example.workoutnotebook.ui.common.BaseTest
import io.mockk.every
import io.mockk.mockk
import io.reactivex.Single
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

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

}