package com.fastholf.templine.presentation

import com.fastholf.templine.domain.RootController
import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.formatter.IAxisValueFormatter
import io.reactivex.android.schedulers.AndroidSchedulers
import org.joda.time.DateTime
import java.util.concurrent.TimeUnit

class RootPresenter(private val rootController: RootController, val view: View) {

	fun onStart() {
		rootController.getForecast()
			.observeOn(AndroidSchedulers.mainThread())
			.subscribe {
				val forecast = it.hours.mapIndexed { index, pair -> Pair(index, pair.second) }
				val xLabels = it.hours.map { (first) -> DateTime(TimeUnit.SECONDS.toMillis(first)).hourOfDay }
				view.showForecast(forecast, XAxisValueFormatter(xLabels))
			}
		rootController.isLoadingForecast()
			.observeOn(AndroidSchedulers.mainThread())
			.subscribe {
				view.showLoading(it)
			}
	}

	private class XAxisValueFormatter(private val xLabels: List<Int>) :IAxisValueFormatter {
		override fun getFormattedValue(value: Float, axis: AxisBase?): String {
			return xLabels[value.toInt()].toString()
		}
	}

	interface View {
		fun showForecast(forecast: List<Pair<Int, Double>>, xAxisValueFormatter: IAxisValueFormatter)
		fun showLoading(loading: Boolean)
	}
}