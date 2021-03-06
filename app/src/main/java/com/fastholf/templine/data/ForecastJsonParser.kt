package com.fastholf.templine.data

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue

class ForecastJsonParser {
	fun parse(jsonString: String): ForecastResponse {
		val jsonForecast = jacksonObjectMapper().readValue<JsonForecast>(jsonString)
		return ForecastResponse(jsonForecast.hourly.data.map { Pair(it.time, it.temperature) })
	}
}

@JsonIgnoreProperties(ignoreUnknown = true)
data class JsonForecast(val hourly: JsonHourly)

@JsonIgnoreProperties(ignoreUnknown = true)
data class JsonHourForecast(val time: Long, val temperature: Double)

@JsonIgnoreProperties(ignoreUnknown = true)
data class JsonHourly(val data: List<JsonHourForecast>)
