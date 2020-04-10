package com.example.workoutapp.ui.register

import com.example.workoutapp.domain.extension.doOnIoObserveOnMain
import com.example.workoutapp.domain.user.UserRepository
import com.example.workoutapp.domain.user.model.UserModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import timber.log.Timber

class RegisterPresenter(
    private val userRepository: UserRepository,
    private val compositeDisposable: CompositeDisposable
) : RegisterContract.Presenter {

    private lateinit var view: RegisterContract.View

    override fun setView(view: RegisterContract.View) {
        this.view = view
    }

    override fun start() {

    }

    override fun finish() {
        compositeDisposable.clear()
    }

    override fun onContinueClicked(username: String, password: String) {
        userRepository.insertUser(UserModel(username, password))
            .doOnIoObserveOnMain()
            .subscribeBy(
                onComplete = {
                    view.nextActivity()
                },
                onError = {
                    Timber.e(it)
                })


            .addTo(compositeDisposable)
    }

}