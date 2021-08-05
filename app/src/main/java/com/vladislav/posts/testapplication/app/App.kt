package com.vladislav.posts.testapplication.app

import android.app.Application
import com.vladislav.posts.testapplication.di.component.AppComponent

class App : Application() {

    val appComponent: AppComponent by lazy { AppComponent(this) }

    override fun onCreate() {
        super.onCreate()

        appComponent.inject(this)
    }
}