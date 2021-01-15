package com.example.workoutnotebook.domain.user

import com.example.workoutnotebook.domain.session.SessionManager
import io.mockk.mockk
import org.junit.Assert.*

class GetCurrentUserUseCaseImplTest {

    private val sessionManager: SessionManager = mockk()
    private val userRepository: UserRepository = mockk()


}