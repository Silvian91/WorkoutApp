package com.example.workoutapp.domain.extension

import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

fun <T> Single<T>.doOnIoObserveOnMain(): Single<T> {
    return observeOn(AndroidSchedulers.mainThread())
        .subscribeOn(Schedulers.io())
        .unsubscribeOn(Schedulers.io())
}

fun Completable.doOnIoObserveOnMain(): Completable {
    return observeOn(AndroidSchedulers.mainThread())
        .subscribeOn(Schedulers.io())
        .unsubscribeOn(Schedulers.io())
}

fun <T> Maybe<T>.maybeDoOnIoObserveOnMain(): Maybe<T> {
    return observeOn(AndroidSchedulers.mainThread())
        .subscribeOn(Schedulers.io())
        .unsubscribeOn(Schedulers.io())
}

//fun String.someName() : SomeReturnType {
//    return SomeReturnType
//}
//
//"".someName()