package com.fastholf.templine.domain

import com.fastholf.templine.data.Repository
import io.reactivex.Observable

/**
 * Created by fastholf on 15/05/2017.
 */
class RootController(private val repository: Repository) {
	fun getResponse(): Observable<Forecast> {
		return repository.getResponse()
	}
}
