package com.fastholf.templine.data

import com.fastholf.templine.domain.Forecast
import com.fastholf.templine.domain.IRepository
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject
import java.net.URL

class Repository(private val cache: Cache) :IRepository {

	private val rootUrl = "https://api.darksky.net/forecast"
	private val secretKey = ""
	private val location = "56.5010,84.9924"
	private val params = "units=si"

	private val isLoading: BehaviorSubject<Boolean> = BehaviorSubject.createDefault(false)

	override fun getForecast(): Observable<Forecast> {
		return Observable
			.create<ForecastResponse> {
				if (cache.isCached()) {
					it.onNext(ForecastJsonParser().parse(cache.getCachedForecastResponse()))
				}
				if (!cache.isCached() || cache.isExpired()) {
					isLoading.onNext(true)
					val jsonResponse = URL("$rootUrl/$secretKey/$location?$params").readText()
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
