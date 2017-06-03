package com.fastholf.templine

import android.app.Application
import net.danlew.android.joda.JodaTimeAndroid


/**
 * Created by fastholf on 03/06/2017.
 */
class TempLineApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        JodaTimeAndroid.init(this)
    }
}