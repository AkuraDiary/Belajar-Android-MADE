package com.dicoding.tourismapp.di

import com.dicoding.tourismapp.core.domain.usecase.TourismInteractor
import com.dicoding.tourismapp.core.domain.usecase.TourismUseCase
import com.dicoding.tourismapp.detail.DetailTourismViewModel
import com.dicoding.tourismapp.favorite.FavoriteViewModel
import com.dicoding.tourismapp.home.HomeViewModel
import dagger.Binds
import dagger.Module
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

@Module
abstract class AppModule{

    @Binds
    abstract fun provideTourismUseCase(tourismInteractor: TourismInteractor) : TourismUseCase

    val useCaseModule = module {
        factory<TourismUseCase> {TourismInteractor(get())}
    }

    val viewModelModule = module {
        viewModel { HomeViewModel(get()) }
        viewModel { FavoriteViewModel(get()) }
        viewModel { DetailTourismViewModel(get()) }
    }
}
