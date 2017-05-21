package com.fastholf.templine

import com.fastholf.templine.forecast.Forecast
import com.fastholf.templine.forecast.ForecastJsonParser
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
    val PARAMS = "units=si"

    fun getResponse(): Observable<Forecast> {
        return Observable.create<Forecast> { e ->
            val response = URL("$ROOT_URL/$SECRET_KEY/$LOCATION?$PARAMS").readText()
            e.onNext(ForecastJsonParser().parse(response))
        }.subscribeOn(Schedulers.io())
    }
}
