package com.example.workoutapp.ui.register

import com.example.workoutapp.domain.extension.doOnIoObserveOnMain
import com.example.workoutapp.domain.user.UserRepository
import com.example.workoutapp.domain.user.model.UserModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo

class RegisterPresenter(
    private val userRepository: UserRepository,
    private val compositeDisposable: CompositeDisposable
) : RegisterContract.Presenter {

    private lateinit var view: RegisterContract.View
    private val user = ArrayList<UserModel>()

    override fun setView(view: RegisterContract.View) {
        this.view = view
    }

    override fun start() {

    }

    override fun finish() {
        compositeDisposable.clear()
    }

    override fun onContinueClicked(username: String, password: String) {
        user.add(UserModel(username, password))
        userRepository.insertUsernameAndPassword(user)
            .doOnIoObserveOnMain()
            .subscribe { view.nextActivity() }
            .addTo(compositeDisposable)
    }

}