package com.haroldcalayan.conversionrates.data.repository

import com.haroldcalayan.conversionrates.common.base.BaseRepository
import com.haroldcalayan.conversionrates.data.source.remote.service.MobimateService

interface MobimateRepository {
    suspend fun getConversionRates(): List<String>
}

class MobimateRepositoryImpl(val api: MobimateService) : BaseRepository(), MobimateRepository {

    override suspend fun getConversionRates(): List<String> {
        var conversions = api.getConversionRates()
        conversions = conversions?.trim()
        return conversions?.split("\n") ?: emptyList()
    }
}