package com.asthiseta.submission1madedicoding.di

import com.asthiseta.core.domain.usecase.ItemInteractor
import com.asthiseta.core.domain.usecase.ItemUseCase
import com.asthiseta.submission1madedicoding.detail.DetailVM
import com.asthiseta.submission1madedicoding.home.HomeVM
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<ItemUseCase> {ItemInteractor(get())}
}

val viewModelModule = module {
    viewModel { HomeVM(get()) }
    viewModel { DetailVM(get()) }
}