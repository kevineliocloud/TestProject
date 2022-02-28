package com.test.testapplication.di.component

import com.test.testapplication.TestApp
import com.test.testapplication.di.builder.ActivityBuilder
import com.test.testapplication.di.module.AppModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidInjectionModule::class, AppModule::class, ActivityBuilder::class])
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: TestApp): Builder
        fun build(): AppComponent
    }

    fun inject(app: TestApp)

}