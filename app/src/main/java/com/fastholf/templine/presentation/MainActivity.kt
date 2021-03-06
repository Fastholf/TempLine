package com.fastholf.templine.presentation

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.fastholf.templine.R
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.IAxisValueFormatter

class MainActivity :AppCompatActivity() {

	private val presenter: RootPresenter by lazy {
		RootPresenter(
			AppInitializer.rootController,
			RootPresenterView()
		)
	}

	private val lineChart: LineChart by lazy { findViewById<LineChart>(R.id.chart) }
	private val process: View by lazy { findViewById<View>(R.id.progress) }

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)
	}

	override fun onStart() {
		super.onStart()
		presenter.onStart()
	}

	inner class RootPresenterView :RootPresenter.View {
		override fun showLoading(loading: Boolean) {
			process.visibility = if (loading) View.VISIBLE else View.GONE
		}

		override fun showForecast(forecast: List<Pair<Int, Double>>, xAxisValueFormatter: IAxisValueFormatter) {
			lineChart.data = LineData(LineDataSet(forecast.map { Entry(it.first.toFloat(), it.second.toFloat()) }, ""))
			lineChart.xAxis.valueFormatter = xAxisValueFormatter
			lineChart.invalidate()
		}
	}
}
