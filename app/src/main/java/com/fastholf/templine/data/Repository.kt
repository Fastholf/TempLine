package com.fastholf.templine.data

import com.fastholf.templine.domain.Forecast
import com.fastholf.templine.domain.IRepository
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject
import java.net.URL

/**
 * Created by fastholf on 15/05/2017.
 */
class Repository(private val cache: Cache) :IRepository {

	val ROOT_URL = "https://api.darksky.net/forecast"
	val SECRET_KEY = ""
	val LOCATION = "56.5010,84.9924"
	val PARAMS = "units=si"

	private val isLoading: BehaviorSubject<Boolean> = BehaviorSubject.createDefault(false)

	override fun getForecast(): Observable<Forecast> {
		return Observable
			.create<ForecastResponse> {
				if (cache.isCached()) {
					it.onNext(ForecastJsonParser().parse(cache.getCachedForecastResponse()))
				}
				if (!cache.isCached() || cache.isExpired()) {
					isLoading.onNext(true)
					val jsonResponse = URL("$ROOT_URL/$SECRET_KEY/$LOCATION?$PARAMS").readText()
					cache.cacheForecastResponse(jsonResponse)
					val forecast = ForecastJsonParser().parse(jsonResponse)
					isLoading.onNext(false)
					it.onNext(forecast)
				}
			}
			.map(ForecastMapper()::transform)
			.subscribeOn(Schedulers.io())
	}

	override fun isLoading(): Observable<Boolean> {
		return isLoading.hide()
	}
}
