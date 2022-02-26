package com.dicoding.mysimplelogin

import com.dicoding.core.SessionManager
import com.dicoding.core.UserRepository
import org.koin.dsl.module

val storageModule = module {
    factory {
        SessionManager(get())
    }

    single {
        UserRepository(get())
    }
}