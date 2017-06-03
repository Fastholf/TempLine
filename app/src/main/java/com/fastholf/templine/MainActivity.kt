package com.fastholf.templine

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.IAxisValueFormatter


class MainActivity : AppCompatActivity() {

    private val presenter: RootPresenter by lazy { RootPresenter(RootController.repository, RootPresenterView()) }

    private val lineChart: LineChart by lazy { findViewById(R.id.chart) as LineChart }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onStart() {
        super.onStart()
        presenter.onStart()
    }

    inner class RootPresenterView : RootPresenter.View {

        override fun showForecast(forecast: List<Pair<Int, Double>>, xAxisValueFormatter: IAxisValueFormatter) {
            lineChart.data = LineData(LineDataSet(forecast.map { Entry(it.first.toFloat(), it.second.toFloat()) }, ""))
            lineChart.xAxis.valueFormatter = xAxisValueFormatter
            lineChart.invalidate()
        }
    }
}
