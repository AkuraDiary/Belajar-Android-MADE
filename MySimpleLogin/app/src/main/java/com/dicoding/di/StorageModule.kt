package com.dicoding.di

import android.content.Context
import com.dicoding.core.SessionManager
import dagger.Module
import dagger.Provides

@Module
class StorageModule {
    @Provides
    fun provideSessionManager(context: Context) : SessionManager = SessionManager(context)
}