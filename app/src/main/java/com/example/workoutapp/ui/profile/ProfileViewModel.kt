package com.example.workoutapp.ui.profile

import com.example.workoutapp.domain.extension.doOnIoObserveOnMain
import com.example.workoutapp.domain.logout.LogoutUseCase
import com.example.workoutapp.domain.logout.LogoutUseCase.Output.ErrorUnknown
import com.example.workoutapp.domain.user.GetCurrentUserUseCase
import com.example.workoutapp.domain.user.GetCurrentUserUseCase.Input
import com.example.workoutapp.domain.user.GetCurrentUserUseCase.Output.ErrorUnauthorized
import com.example.workoutapp.ui.profile.adapter.ProfileItemWrapper
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import com.example.workoutapp.domain.logout.LogoutUseCase.Output.Success as LogoutSuccess
import com.example.workoutapp.domain.user.GetCurrentUserUseCase.Output.Success as GetUserSuccess

class ProfilePresenter(
    private val getCurrentUserUseCase: GetCurrentUserUseCase,
    private val logoutUseCase: LogoutUseCase,
    private val compositeDisposable: CompositeDisposable
) : ProfileContract.Presenter {

    private lateinit var viewState: ProfileViewState

    private lateinit var items: MutableList<ProfileItemWrapper>

    init {

        items = mutableListOf(ProfileItemWrapper.Profile(), ProfileItemWrapper.Heading)
        viewState = ProfileViewState(items)
    }

    private lateinit var view: ProfileContract.View

    override fun setView(view: ProfileContract.View) {
        this.view = view
    }

    override fun start() {
        getCurrentUserUseCase.execute(Input)
            .doOnIoObserveOnMain()
            .subscribeBy {
                when (it) {
                    is GetUserSuccess -> {
                        items[0] = ProfileItemWrapper.Profile(it.user.username)
                        viewState.copy(items)
                        view.render(viewState)
                    }
                    is ErrorUnauthorized -> view.showLogin()
                    else -> view.showError()
                }
            }
            .addTo(compositeDisposable)
    }

    override fun onLogOutConfirmed() {
        logoutUseCase.execute(LogoutUseCase.Input)
            .doOnIoObserveOnMain()
            .subscribeBy {
                when (it) {
                    is LogoutSuccess -> view.showLoginActivity()
                    is ErrorUnknown -> view.showError()
                }
            }
            .addTo(compositeDisposable)

    }

    override fun logOutClicked() {
        view.showLogOutConfirmationDialog()
    }

    override fun onGalleryClicked() {
        view.checkPermissionForImage()
    }

    override fun onCameraClicked() {
        view.openCamera()
    }

    override fun onGallerySelected() {
        view.openGallery()
    }

    override fun finish() = compositeDisposable.clear()
}