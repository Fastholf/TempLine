package com.fastholf.templine.data

import android.content.Context
import android.content.SharedPreferences
import java.util.concurrent.TimeUnit

class Cache(context: Context) {
	private val fileName = "forecast_cache"
	private val responseKey = "response_key"
	private val timestampKey = "timestamp_key"

	private val expirationTime = TimeUnit.MINUTES.toMillis(30)

	private val storage: SharedPreferences = context.getSharedPreferences(fileName, Context.MODE_PRIVATE)

	fun cacheForecastResponse(response: String) {
		with(storage.edit()) {
			putString(responseKey, response)
			putLong(timestampKey, System.currentTimeMillis())
			apply()
		}
	}

	fun getCachedForecastResponse(): String {
		return storage.getString(responseKey, "")
	}

	fun isCached(): Boolean {
		return storage.contains(responseKey)
	}

	fun isExpired(): Boolean {
		return System.currentTimeMillis() - storage.getLong(timestampKey, 0) > expirationTime
	}
}
