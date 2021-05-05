package com.rosianu.workoutnotebook.ui.common

import androidx.annotation.CallSuper
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach

open class BaseTest {

    @BeforeEach
    @CallSuper
    open fun setUp(){
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
        RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }
        RxJavaPlugins.setComputationSchedulerHandler { Schedulers.trampoline() }
    }

    @AfterEach
    @CallSuper
    open fun tearDown(){
        RxAndroidPlugins.reset()
        RxJavaPlugins.reset()
    }

}