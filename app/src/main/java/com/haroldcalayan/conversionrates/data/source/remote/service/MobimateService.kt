package com.haroldcalayan.conversionrates.data.source.remote.service

import retrofit2.http.GET

interface MobimateService {

    @GET("content/worldmate/currencies/currency2008.dat")
    suspend fun getConversionRates(): String?

}