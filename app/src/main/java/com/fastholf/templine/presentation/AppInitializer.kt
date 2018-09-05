package com.fastholf.templine.presentation

import android.app.Application
import com.fastholf.templine.data.Repository
import com.fastholf.templine.domain.RootController
import net.danlew.android.joda.JodaTimeAndroid
import kotlin.properties.Delegates

object AppInitializer {
	var rootController: RootController by Delegates.notNull()

	fun init(application: Application) {
		JodaTimeAndroid.init(application)
		rootController = RootController(Repository())
	}
}
