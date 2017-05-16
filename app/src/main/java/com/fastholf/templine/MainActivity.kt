package com.fastholf.templine

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.TextView
import android.text.method.ScrollingMovementMethod

class MainActivity : AppCompatActivity() {

    private val presenter: RootPresenter by lazy { RootPresenter(RootController.repository, RootPresenterView()) }

    private val rawResponseText: TextView by lazy { findViewById(R.id.raw_response) as TextView }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        rawResponseText.movementMethod = ScrollingMovementMethod.getInstance()
    }

    override fun onStart() {
        super.onStart()
        presenter.onStart()
    }

    inner class RootPresenterView : RootPresenter.View {

        override fun showResponse(response: String) {
            rawResponseText.text = response
        }
    }
}
