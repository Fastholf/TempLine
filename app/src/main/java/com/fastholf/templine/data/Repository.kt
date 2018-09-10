package com.fastholf.templine.data

import com.fastholf.templine.domain.Forecast
import com.fastholf.templine.domain.IRepository
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import java.net.URL

/**
 * Created by fastholf on 15/05/2017.
 */
class Repository(private val cache: Cache) :IRepository {

	val ROOT_URL = "https://api.darksky.net/forecast"
	val SECRET_KEY = ""
	val LOCATION = "56.5010,84.9924"
	val PARAMS = "units=si"

	override fun getForecast(): Observable<Forecast> {
		return Observable
			.create<ForecastResponse> {
				if (cache.isCached()) {
					it.onNext(ForecastJsonParser().parse(cache.getCachedForecastResponse()))
				}
				if (!cache.isCached() || cache.isExpired()) {
					val jsonResponse = URL("$ROOT_URL/$SECRET_KEY/$LOCATION?$PARAMS").readText()
					cache.cacheForecastResponse(jsonResponse)
					it.onNext(ForecastJsonParser().parse(jsonResponse))
				}
			}
			.map(ForecastMapper()::transform)
			.subscribeOn(Schedulers.io())
	}
}
