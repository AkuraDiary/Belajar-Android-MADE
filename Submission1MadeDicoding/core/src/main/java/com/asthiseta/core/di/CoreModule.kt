package com.asthiseta.core.di

import androidx.room.Room
import com.asthiseta.core.BuildConfig
import com.asthiseta.core.data.ItemRepos
import com.asthiseta.core.data.source.local.LocalDataSource
import com.asthiseta.core.data.source.local.room.KosDatabase
import com.asthiseta.core.data.source.remote.RemoteDataSource
import com.asthiseta.core.data.source.remote.network.ClientApi
import com.asthiseta.core.domain.repository.IItemRepository
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit


private const val BASE_URL = BuildConfig.BASE_URL

val databaseModule = module{
    factory {
        get<KosDatabase>().kosDao()
    }
    single {
        Room.databaseBuilder(
            androidContext(),
            KosDatabase::class.java, "Kos.db"
        ).fallbackToDestructiveMigration().build()
    }
}

val networkModule = module {
    single {
        OkHttpClient.Builder()
            .addInterceptor{ chain ->
                val original = chain.request()
                val requestBuilder = original.newBuilder()
                val request = requestBuilder.build()
                chain.proceed(request)
            }
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .build()
    }

    single {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .client(get())
            .build()
        retrofit.create(ClientApi::class.java)
    }
}

val repositoryModule = module {
    single { LocalDataSource(get()) }
    single { RemoteDataSource(get()) }
    single<IItemRepository>{
        ItemRepos(
            get(),
            get()
        )
    }
}