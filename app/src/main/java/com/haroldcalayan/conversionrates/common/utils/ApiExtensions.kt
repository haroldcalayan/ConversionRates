package com.haroldcalayan.conversionrates.common.utils

import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

inline fun <reified T>provideApiUsingMoshi(apiUrl: String, httpClient: OkHttpClient): T {
    return Retrofit.Builder()
        .baseUrl(apiUrl)
        .addConverterFactory(MoshiConverterFactory.create().asLenient())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
        .client(httpClient)
        .build()
        .create(T::class.java)
}

inline fun <reified T>provideApiUsingScalar(apiUrl: String, httpClient: OkHttpClient): T {
    return Retrofit.Builder()
        .baseUrl(apiUrl)
        .addConverterFactory(ScalarsConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
        .client(httpClient)
        .build()
        .create(T::class.java)
}