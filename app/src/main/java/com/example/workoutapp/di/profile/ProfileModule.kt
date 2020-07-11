package com.example.workoutapp.di.profile

import com.example.workoutapp.domain.logout.LogoutUseCase
import com.example.workoutapp.domain.logout.LogoutUseCaseImpl
import com.example.workoutapp.domain.session.SessionManager
import com.example.workoutapp.domain.user.GetCurrentUserUseCase
import com.example.workoutapp.ui.profile.ProfileContract
import com.example.workoutapp.ui.profile.ProfilePresenter
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable

@Module
class ProfileModule {

    @Provides
    fun providesProfilePresenter(
        getCurrentUserUseCase: GetCurrentUserUseCase,
        logoutUseCase: LogoutUseCase,
        compositeDisposable: CompositeDisposable
    ): ProfileContract.Presenter {
        return ProfilePresenter(getCurrentUserUseCase, logoutUseCase, compositeDisposable)
    }

    @Provides
    fun providesLogoutUseCase(sessionManager: SessionManager): LogoutUseCase {
        return LogoutUseCaseImpl(sessionManager)
    }

}