package com.example.sportie.di

import android.content.Context
import com.example.data.network.ApiInterface
import com.example.sportie.BuildConfig
import com.squareup.moshi.Moshi
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level.BODY
import okhttp3.logging.HttpLoggingInterceptor.Level.NONE
import org.koin.core.scope.Scope
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create

val networkModule = module {
    single { Moshi.Builder().build() }
    single { provideRetrofit() }
    single { provideApiInterface() }
    single { provideHttpClient(get()) }
}

private fun Scope.provideApiInterface(): ApiInterface {
    return get<Retrofit>().create()
}

private fun provideHttpClient(context: Context): OkHttpClient {
    val size: Long = 10 * 1024 * 1024 // 10 MB
    val cache = Cache(context.cacheDir, size)
    return OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG) BODY else NONE
        })
//        .cache(cache)
        .build()
}

private fun Scope.provideRetrofit(): Retrofit {
    return Retrofit.Builder()
        .client(get())
        .baseUrl(BuildConfig.BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create(get()))
        .build()
}