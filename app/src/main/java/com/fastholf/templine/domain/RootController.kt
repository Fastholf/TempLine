package com.fastholf.templine.domain

import io.reactivex.Observable

class RootController(private val repository: IRepository) {
	fun getForecast(): Observable<Forecast> {
		return repository.getForecast()
	}

	fun isLoadingForecast(): Observable<Boolean> {
		return repository.isLoading()
	}
}
