package com.example.workoutapp.domain.extension

import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.io.IOException

fun <T> Single<T>.doOnIoObserveOnMain(): Single<T> {
    return observeOn(AndroidSchedulers.mainThread())
        .doOnError { println("Doing on error") }
        .subscribeOn(Schedulers.io())
        .unsubscribeOn(Schedulers.io())
}

fun Completable.doOnIoObserveOnMain(): Completable {
    return observeOn(AndroidSchedulers.mainThread())
        .doOnError { println("Doing on error") }
        .subscribeOn(Schedulers.io())
        .unsubscribeOn(Schedulers.io())
}

//fun String.someName() : SomeReturnType {
//    return SomeReturnType
//}
//
//"".someName()