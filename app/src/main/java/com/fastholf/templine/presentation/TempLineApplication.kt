package com.fastholf.templine.presentation

import android.app.Application

@Suppress("unused")
class TempLineApplication :Application() {
	override fun onCreate() {
		super.onCreate()
		AppInitializer.init(this)
	}
}
