package com.test.testapplication.utils

import com.test.testapplication.BuildConfig
import timber.log.Timber


// All Logger
object AppLogger {
    fun d(s: String?, vararg objects: Any?) {
        Timber.d(s, *objects)
    }

    fun init() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}