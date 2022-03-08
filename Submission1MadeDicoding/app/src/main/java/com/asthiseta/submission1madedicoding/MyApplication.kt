package com.asthiseta.submission1madedicoding

import android.app.Application
import com.asthiseta.core.di.databaseModule
import com.asthiseta.core.di.networkModule
import com.asthiseta.core.di.repositoryModule
import com.asthiseta.submission1madedicoding.di.useCaseModule
import com.asthiseta.submission1madedicoding.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin{
            androidLogger(Level.NONE)
            androidContext(this@MyApplication)
            modules(
                listOf(
                    databaseModule,
                    networkModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelModule
                )
            )
        }
    }
}