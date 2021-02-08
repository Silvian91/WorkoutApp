package com.example.workoutnotebook.domain.location

import com.example.workoutnotebook.domain.location.model.LocationModel
import io.mockk.mockk
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach

internal class GetLocationUseCaseImplTest {

    private val repository: LocationRepository = mockk()
    private lateinit var useCase: GetLocationUseCase
    private var model = LocationModel(11.22, 22.11)

    @BeforeEach
    fun setUp() {
        useCase = GetLocationUseCaseImpl(repository)
        RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }
    }

    @AfterEach
    fun tearDown() {
        RxAndroidPlugins.reset()
        RxJavaPlugins.reset()
    }

//    @Test
//    fun `verify on successful execution success output gets returned`() {
//        every { repository.getCurrentLocation() } returns
//
//        useCase.execute(GetLocationUseCase.Input).test()
//            .assertValue(GetLocationUseCase.Output.Success(model))
//    }
//
//    @Test
//    fun `verify exceptions from source get mapped to unknown error`() {
//        every { repository.getCurrentLocation() } returns
//
//        useCase.execute(GetLocationUseCase.Input).test()
//            .assertValue(GetLocationUseCase.Output.NetworkError)
//    }

}