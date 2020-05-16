package com.example.workoutapp.ui.profile

import com.example.workoutapp.domain.extension.doOnIoObserveOnMain
import com.example.workoutapp.domain.user.GetCurrentUserUseCase
import com.example.workoutapp.domain.user.GetCurrentUserUseCase.Input
import com.example.workoutapp.domain.user.GetCurrentUserUseCase.Output.ErrorUnauthorized
import com.example.workoutapp.domain.user.GetCurrentUserUseCase.Output.Success
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy

class ProfilePresenter(
    private val getCurrentUserUseCase: GetCurrentUserUseCase,
    private val compositeDisposable: CompositeDisposable
) : ProfileContract.Presenter {

    private lateinit var view: ProfileContract.View

    override fun setView(view: ProfileContract.View) {
        this.view = view
    }

    override fun start() {
        getCurrentUserUseCase.execute(Input)
            .doOnIoObserveOnMain()
            .subscribeBy {
                when (it) {
                    //TODO: Dependency injection
                    is Success -> view.showUsername(it.user.username)
                    is ErrorUnauthorized -> view.showLogin()
                    else -> view.showError()
                }
            }
            .addTo(compositeDisposable)
    }

    override fun onWorkoutClicked() {
        view.nextActivity()
    }

    override fun finish() = compositeDisposable.clear()
}