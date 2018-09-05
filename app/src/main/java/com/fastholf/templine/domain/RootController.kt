package com.fastholf.templine.domain

import io.reactivex.Observable

/**
 * Created by fastholf on 15/05/2017.
 */
class RootController(private val repository: IRepository) {
	fun getForecast(): Observable<Forecast> {
		return repository.getForecast()
	}
}
