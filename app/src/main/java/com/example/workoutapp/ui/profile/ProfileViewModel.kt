package com.example.workoutapp.ui.profile

import android.graphics.Bitmap
import com.example.workoutapp.R
import com.example.workoutapp.R.string.text_profile_header
import com.example.workoutapp.domain.extension.doOnIoObserveOnMain
import com.example.workoutapp.domain.logout.LogoutUseCase
import com.example.workoutapp.domain.logout.LogoutUseCase.Output.ErrorUnknown
import com.example.workoutapp.domain.profile.GetUserRoutinesUseCase
import com.example.workoutapp.domain.showworkout.GetWorkoutUseCase
import com.example.workoutapp.domain.user.GetCurrentUserUseCase
import com.example.workoutapp.domain.user.GetCurrentUserUseCase.Input
import com.example.workoutapp.domain.user.GetCurrentUserUseCase.Output.ErrorUnauthorized
import com.example.workoutapp.ui.common.BaseViewModel
import com.example.workoutapp.ui.error.ErrorType.Unknown
import com.example.workoutapp.ui.profile.adapter.ProfileItemWrapper
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.subjects.BehaviorSubject
import javax.inject.Inject
import com.example.workoutapp.domain.logout.LogoutUseCase.Output.Success as LogoutSuccess
import com.example.workoutapp.domain.profile.GetUserRoutinesUseCase.Output.Success as SuccessUserRoutines
import com.example.workoutapp.domain.profile.GetUserRoutinesUseCase.Output.SuccessNoRoutines as NoUserRoutines
import com.example.workoutapp.domain.showworkout.GetWorkoutUseCase.Output.Success as SuccessWorkouts
import com.example.workoutapp.domain.showworkout.GetWorkoutUseCase.Output.SuccessNoData as SuccessNoWorkouts
import com.example.workoutapp.domain.user.GetCurrentUserUseCase.Output.Success as GetUserSuccess

class ProfileViewModel @Inject constructor(
    private val getCurrentUserUseCase: GetCurrentUserUseCase,
    private val getWorkoutUseCase: GetWorkoutUseCase,
    private val getUserRoutinesUseCase: GetUserRoutinesUseCase,
    private val logoutUseCase: LogoutUseCase
) : BaseViewModel() {

    private var items = mutableListOf(
        ProfileItemWrapper.Profile(defaultUserPic = R.drawable.ic_profile_image),
        ProfileItemWrapper.Heading(text_profile_header),
        ProfileItemWrapper.WorkoutsCount(),
        ProfileItemWrapper.RoutinesCount()
    )

    private var currentViewState = ProfileViewState(
        items,
        login = false,
        bottomSheetDialog = false,
        cameraClicked = false,
        permissionCheck = false,
        galleryPermission = false
    )

    val viewState = BehaviorSubject.createDefault(currentViewState)

    init {
        getStatistics()
    }

    private fun getStatistics() {
        getCurrentUserUseCase.execute(Input)
            .doOnIoObserveOnMain()
            .subscribeBy {
                when (it) {
                    is GetUserSuccess -> {
                        items[0] = (items[0] as ProfileItemWrapper.Profile).copy(it.user.username)
                        currentViewState.copy(items)
                        getWorkoutsCount(it.user.id!!)
                        getRoutinesCount(it.user.id!!)
                    }
                    is ErrorUnauthorized ->
                        currentViewState = currentViewState.copy(login = true)
                    else -> error.onNext(Unknown)
                }
                viewState.onNext(currentViewState)
            }
            .addTo(compositeDisposable)
    }

    private fun getWorkoutsCount(id: Long) {
        getWorkoutUseCase.execute(GetWorkoutUseCase.Input(id))
            .doOnIoObserveOnMain()
            .subscribeBy { output ->
                when (output) {
                    is SuccessNoWorkouts -> {
                        items[2] = ProfileItemWrapper.WorkoutsCount()
                    }
                    is SuccessWorkouts -> {
                        items[2] = ProfileItemWrapper.WorkoutsCount(output.workouts.size)
                    }
                    else -> error.onNext(Unknown)
                }
                currentViewState.copy(items)
                viewState.onNext(currentViewState)
            }
            .addTo(compositeDisposable)
    }

    private fun getRoutinesCount(id: Long) {
        getUserRoutinesUseCase.execute(GetUserRoutinesUseCase.Input(id))
            .doOnIoObserveOnMain()
            .subscribeBy {
                when (it) {
                    is SuccessUserRoutines -> {
                        items[3] = ProfileItemWrapper.RoutinesCount(it.routines.size)
                    }
                    is NoUserRoutines -> {
                        items[3] = ProfileItemWrapper.RoutinesCount()
                    }
                    else -> error.onNext(Unknown)
                }
                currentViewState.copy(items)
                viewState.onNext(currentViewState)
            }
            .addTo(compositeDisposable)
    }

    fun onLogOutConfirmed() {
        logoutUseCase.execute(LogoutUseCase.Input)
            .doOnIoObserveOnMain()
            .subscribeBy {
                when (it) {
                    is LogoutSuccess ->
                        currentViewState = currentViewState.copy(login = true)
                    is ErrorUnknown -> error.onNext(Unknown)
                }
                viewState.onNext(currentViewState)
            }
            .addTo(compositeDisposable)
    }

    fun onBottomSheetClicked() {
        currentViewState = currentViewState.copy(bottomSheetDialog = true)
        viewState.onNext(currentViewState)
    }

    private fun bottomSheetDismissed() {
        currentViewState = currentViewState.copy(bottomSheetDialog = false)
    }

    fun onCameraClicked() {
        currentViewState = currentViewState.copy(cameraClicked = true)
        bottomSheetDismissed()
        viewState.onNext(currentViewState)
    }

    private fun cameraDismissed() {
        currentViewState = currentViewState.copy(cameraClicked = false)
    }

    fun onGalleryClicked() {
        currentViewState = currentViewState.copy(permissionCheck = true)
        bottomSheetDismissed()
        viewState.onNext(currentViewState)
    }

    private fun dismissPermissionCheck() {
        currentViewState = currentViewState.copy(permissionCheck = false)
    }

    fun onGalleryOption() {
        currentViewState = currentViewState.copy(galleryPermission = true)
        dismissPermissionCheck()
        viewState.onNext(currentViewState)
    }

    private fun dismissGalleryView() {
        currentViewState = currentViewState.copy(galleryPermission = false)
    }

    fun setImage(imageBitmap: Bitmap) {
        items[0] = (items[0] as ProfileItemWrapper.Profile).copy(profilePicture = imageBitmap)
        currentViewState.copy(items)
        cameraDismissed()
        dismissPermissionCheck()
        dismissGalleryView()
        bottomSheetDismissed()
        viewState.onNext(currentViewState)
    }

}