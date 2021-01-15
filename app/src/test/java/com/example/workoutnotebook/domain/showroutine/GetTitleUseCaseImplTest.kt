package com.example.workoutnotebook.domain.showroutine

import com.example.workoutnotebook.domain.workout.WorkoutRepository
import io.mockk.mockk
import org.junit.Assert.*
import org.junit.jupiter.api.BeforeEach

class GetTitleUseCaseImplTest {

    private val workoutRepository: WorkoutRepository = mockk()

    private lateinit var useCase: GetTitleUseCase

    @BeforeEach
     fun setup() {
        useCase = GetTitleUseCaseImpl(workoutRepository)
    }

}