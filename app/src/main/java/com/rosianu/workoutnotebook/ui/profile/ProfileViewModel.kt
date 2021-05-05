package com.rosianu.workoutnotebook.ui.profile

import android.content.SharedPreferences
import android.graphics.Bitmap
import com.rosianu.lib_image_loader.ImageLoader
import com.rosianu.lib_image_loader.Source.FILE_SYSTEM
import com.rosianu.workoutnotebook.R.string.text_profile_header
import com.rosianu.workoutnotebook.domain.extension.doOnIoObserveOnMain
import com.rosianu.workoutnotebook.domain.logout.LogoutUseCase
import com.rosianu.workoutnotebook.domain.logout.LogoutUseCase.Output.ErrorUnknown
import com.rosianu.workoutnotebook.domain.profile.GetUserRoutinesUseCase
import com.rosianu.workoutnotebook.domain.showworkout.GetWorkoutUseCase
import com.rosianu.workoutnotebook.domain.user.GetCurrentUserUseCase
import com.rosianu.workoutnotebook.domain.user.GetCurrentUserUseCase.Input
import com.rosianu.workoutnotebook.domain.user.GetCurrentUserUseCase.Output.ErrorUnauthorized
import com.rosianu.core.ui.BaseViewModel
import com.rosianu.core.ui.error.ErrorType.Unknown
import com.rosianu.workoutnotebook.ui.profile.adapter.ProfileItemWrapper
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.subjects.BehaviorSubject
import javax.inject.Inject
import com.rosianu.workoutnotebook.domain.logout.LogoutUseCase.Output.Success as LogoutSuccess
import com.rosianu.workoutnotebook.domain.profile.GetUserRoutinesUseCase.Output.Success as SuccessUserRoutines
import com.rosianu.workoutnotebook.domain.profile.GetUserRoutinesUseCase.Output.SuccessNoRoutines as NoUserRoutines
import com.rosianu.workoutnotebook.domain.showworkout.GetWorkoutUseCase.Output.Success as SuccessWorkouts
import com.rosianu.workoutnotebook.domain.showworkout.GetWorkoutUseCase.Output.SuccessNoData as SuccessNoWorkouts
import com.rosianu.workoutnotebook.domain.user.GetCurrentUserUseCase.Output.Success as GetUserSuccess

class ProfileViewModel @Inject constructor(
    private val getCurrentUserUseCase: GetCurrentUserUseCase,
    private val getWorkoutUseCase: GetWorkoutUseCase,
    private val getUserRoutinesUseCase: GetUserRoutinesUseCase,
    private val logoutUseCase: LogoutUseCase,
    private val sharedPreferences: SharedPreferences
) : BaseViewModel() {

    private var items = mutableListOf(
        ProfileItemWrapper.Profile(),
        ProfileItemWrapper.Heading(text_profile_header),
        ProfileItemWrapper.WorkoutsCount(),
        ProfileItemWrapper.RoutinesCount()
    )

    private var currentViewState = ProfileViewState(
        items,
        loading = false,
        login = false,
        bottomSheetDialog = false,
        cameraOpen = false,
        permissionCheck = false,
        galleryOpen = false
    )

    val viewState = BehaviorSubject.createDefault(currentViewState)

    init {
        getStatistics()
    }

    private var userId: Long = 0

    private fun getStatistics() {
        getCurrentUserUseCase.execute(Input)
            .doOnIoObserveOnMain()
            .subscribeBy {
                when (it) {
                    is GetUserSuccess -> {
                        userId = it.user.id!!
                        items[0] =
                            (items[0] as ProfileItemWrapper.Profile).copy(username = it.user.username)
                        currentViewState.copy(items = items, loading = true)
                        getWorkoutsCount(it.user.id!!)
                        getRoutinesCount(it.user.id)
                        getProfilePicture()
                    }
                    is ErrorUnauthorized ->
                        currentViewState = currentViewState.copy(login = true)
                    else -> error.onNext(Unknown)
                }
                viewState.onNext(currentViewState)
            }
            .addTo(compositeDisposable)
    }

    fun getWorkoutsCount(id: Long) {
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
                currentViewState = currentViewState.copy(items = items)
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
                currentViewState = currentViewState.copy(items = items)
                viewState.onNext(currentViewState)
            }
            .addTo(compositeDisposable)
    }

    private fun getProfilePicture() {
        if (ImageLoader(sharedPreferences).loadImage(
                "$PROFILE_IMAGE_PREFIX$userId"
            ) != null
        ) {
            items[0] =
                (items[0] as ProfileItemWrapper.Profile)
                    .copy(
                        profilePicture = ImageLoader(sharedPreferences).loadImage("$PROFILE_IMAGE_PREFIX$userId")
                    )
            currentViewState.copy(items = items)
        } else {
            currentViewState.copy(items = items)
        }
        viewState.onNext(currentViewState)
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
        currentViewState = currentViewState.copy(cameraOpen = true)
        bottomSheetDismissed()
        viewState.onNext(currentViewState)
    }

    fun onGalleryClicked() {
        currentViewState = currentViewState.copy(permissionCheck = true)
        bottomSheetDismissed()
        viewState.onNext(currentViewState)
    }

    fun onGalleryOption() {
        currentViewState = currentViewState.copy(galleryOpen = true, permissionCheck = false)
        viewState.onNext(currentViewState)
    }

    fun onCameraDismissed() {
        currentViewState = currentViewState.copy(cameraOpen = false)
        viewState.onNext(currentViewState)
    }

    fun onGalleryDismissed() {
        currentViewState = currentViewState.copy(galleryOpen = false)
        viewState.onNext(currentViewState)
    }

    fun onImageSelected(imageBitmap: Bitmap) {
        ImageLoader(sharedPreferences).storeImage(
            imageBitmap,
            FILE_SYSTEM,
            "$PROFILE_IMAGE_PREFIX$userId"
        )
            .doOnIoObserveOnMain()
            .subscribeBy(
                onError = {
                    error.onNext(Unknown)
                },
                onComplete = {
                    items[0] =
                        (items[0] as ProfileItemWrapper.Profile).copy(profilePicture = imageBitmap)
                    bottomSheetDismissed()
                    currentViewState = currentViewState.copy(items = items, permissionCheck = false)
                    viewState.onNext(currentViewState)
                }
            )
            .addTo(compositeDisposable)
    }

    companion object {
        private const val PROFILE_IMAGE_PREFIX = "profile-image-"
    }

}