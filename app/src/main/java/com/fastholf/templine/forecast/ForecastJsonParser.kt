package com.fastholf.templine.forecast

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue

/**
 * Created by fastholf on 22/05/2017.
 */
class ForecastJsonParser {
    fun parse(jsonString: String): Forecast {
        val jsonForecast = jacksonObjectMapper().readValue<JsonForecast>(jsonString)
        return Forecast(jsonForecast.hourly.data.map { Pair(it.time, it.temperature) })
    }
}

@JsonIgnoreProperties(ignoreUnknown = true)
data class JsonForecast(val hourly: JsonHourly)

@JsonIgnoreProperties(ignoreUnknown = true)
data class JsonHourForecast(val time: Long, val temperature: Double)

@JsonIgnoreProperties(ignoreUnknown = true)
data class JsonHourly(val data: List<JsonHourForecast>)