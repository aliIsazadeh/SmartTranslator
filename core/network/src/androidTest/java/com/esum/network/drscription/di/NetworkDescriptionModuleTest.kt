package com.esum.network.drscription.di

import com.esum.network.description.api.DescriptionApiService
import com.esum.network.description.di.DescriptionNetworkModule
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@TestInstallIn(
    replaces = [DescriptionNetworkModule::class],
    components = [SingletonComponent::class]
)
@Module
object NetworkDescriptionModuleTest {
    @Singleton
    @Provides
    fun provideDescriptionApi(): DescriptionApiService {
        val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()


        return Retrofit.Builder().baseUrl("https://api.dictionaryapi.dev/api/v2/")
            .addConverterFactory(MoshiConverterFactory.create(moshi)).build()
            .create(DescriptionApiService::class.java)
    }

}