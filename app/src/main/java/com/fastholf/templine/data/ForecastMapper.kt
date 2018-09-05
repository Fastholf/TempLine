package com.fastholf.templine.data

import com.fastholf.templine.domain.Forecast

class ForecastMapper {
	fun transform(forecastResponse: ForecastResponse): Forecast {
		return Forecast(forecastResponse.hours)
	}
}
