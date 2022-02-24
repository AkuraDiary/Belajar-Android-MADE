package com.dicoding

import android.app.Application
import com.dicoding.di.AppComponent
import com.dicoding.di.DaggerAppComponent

class MyApplication : Application() {
    val appComponent : AppComponent by lazy{
        DaggerAppComponent.factory().create(
            applicationContext
        )
    }
}