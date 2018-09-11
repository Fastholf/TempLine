package com.fastholf.templine.domain

import io.reactivex.Observable

interface IRepository {
	fun getForecast(): Observable<Forecast>
	fun isLoading(): Observable<Boolean>
}
