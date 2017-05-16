package com.fastholf.templine

import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import java.net.URL

/**
 * Created by fastholf on 15/05/2017.
 */
class Repository {
    val ROOT_URL = "https://api.darksky.net/forecast"
    val SECRET_KEY = ""
    val LOCATION = "37.8267,-122.4233"

    fun getResponse(): Observable<String> {
        return Observable.create<String> { e -> e.onNext((URL("$ROOT_URL/$SECRET_KEY/$LOCATION").readText())) }
                .subscribeOn(Schedulers.io())
    }
}
