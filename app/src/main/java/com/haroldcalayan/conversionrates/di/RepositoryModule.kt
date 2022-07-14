package com.haroldcalayan.conversionrates.di

import com.haroldcalayan.conversionrates.data.repository.MobimateRepository
import com.haroldcalayan.conversionrates.data.repository.MobimateRepositoryImpl
import com.haroldcalayan.conversionrates.data.source.remote.service.MobimateService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideGithubRepository(api: MobimateService): MobimateRepository {
        return MobimateRepositoryImpl(api)
    }
}