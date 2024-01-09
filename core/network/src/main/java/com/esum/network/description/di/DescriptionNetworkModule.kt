package com.esum.network.description.di

import com.esum.network.description.api.DescriptionApiService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DescriptionNetworkModule {

    @Singleton
    @Provides
    fun provideDescriptionApi(): DescriptionApiService {
        val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory::class).build()
        return Retrofit.Builder().baseUrl("https://api.dictionaryapi.dev/api/v2/")
            .addConverterFactory(MoshiConverterFactory.create(moshi)).build()
            .create(DescriptionApiService::class.java)
    }
}