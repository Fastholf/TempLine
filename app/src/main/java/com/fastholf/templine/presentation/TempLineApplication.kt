package com.fastholf.templine.presentation

import android.app.Application

/**
 * Created by fastholf on 03/06/2017.
 */
class TempLineApplication :Application() {
	override fun onCreate() {
		super.onCreate()
		AppInitializer.init(this)
	}
}
