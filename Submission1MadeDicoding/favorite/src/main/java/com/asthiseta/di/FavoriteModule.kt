package com.asthiseta.di

import com.asthiseta.favorite.FavoriteVM
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val favoriteModule = module{
    viewModel { FavoriteVM(get()) }
}