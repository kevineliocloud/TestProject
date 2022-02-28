package com.test.testapplication

import android.app.Application
import androidx.lifecycle.LifecycleObserver
import com.test.testapplication.data.remote.APIManager
import com.test.testapplication.di.AppInjector
import com.test.testapplication.utils.AppLogger
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class TestApp : Application(), HasAndroidInjector, LifecycleObserver{

    @Inject
    lateinit var activityDispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    override fun androidInjector(): AndroidInjector<Any> {
        return activityDispatchingAndroidInjector
    }

    override fun onCreate() {
        super.onCreate()

        // Init AppLog Utils
        AppLogger.init()

        // Init API manager
        APIManager.init(this)

        // Init AppInjector
        AppInjector.init(this)
    }
}