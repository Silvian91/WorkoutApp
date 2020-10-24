package com.example.workoutapp.ui.profile

import android.content.SharedPreferences
import android.graphics.Bitmap
import com.example.lib_image_loader.ImageLoader
import com.example.lib_image_loader.Source.KEY_VALUE_STORAGE
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
    private val logoutUseCase: LogoutUseCase,
    //TODO: TO BE REFACTORED ....... NEVER!
    private val sharedPreferences: SharedPreferences
) : BaseViewModel() {

    private var items = mutableListOf(
        ProfileItemWrapper.Profile(defaultUserPic = R.drawable.ic_profile_image),
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

    //TODO: Remove
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
        ImageLoader.storeImage(
            imageBitmap,
            KEY_VALUE_STORAGE,
            sharedPreferences,
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