package com.test.testapplication.di.module

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.test.testapplication.TestApp
import com.test.testapplication.utils.AppSchedulerProvider
import com.test.testapplication.utils.SchedulerProvider
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Singleton


@Module
class AppModule {

    @Provides
    @Singleton
    fun provideContext(application: TestApp): Context {
        return application
    }

    @Provides
    @Singleton
    fun provideCompositeDisposable(): CompositeDisposable {
        return CompositeDisposable()
    }

    @Provides
    @Singleton
    fun provideGson(): Gson? {
        return GsonBuilder().excludeFieldsWithoutExposeAnnotation().create()
    }

    @Provides
    fun provideSchedulerProvider(): SchedulerProvider {
        return AppSchedulerProvider()
    }
}