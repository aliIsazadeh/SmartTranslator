package com.esum.network.dataproviders.di

import com.esum.network.NetworkModule
import com.esum.network.TranslateApiService
import com.esum.network.interceptor.TranslateInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@TestInstallIn(replaces = [NetworkModule::class], components = [SingletonComponent::class])
object NetworkModuleTest {


    @Provides
    @Singleton
    fun provideInterceptor(): Interceptor {
        return TranslateInterceptor()
    }

    @Provides
    @Singleton
    fun providesClient(): OkHttpClient {
        return OkHttpClient.Builder().addInterceptor(Interceptor.invoke {
            val request =  it.request().newBuilder()
                .addHeader("X-RapidAPI-Key", "a8c484a0b1mshd2eb9728993d4c4p13b074jsne7ae553be513")
                .addHeader("X-RapidAPI-Host", "translated-mymemory---translation-memory.p.rapidapi.com")
                .build()
            it.proceed(request)
        }).build()
    }


    @Provides
    @Singleton
    fun providesTranslateApiService(okHttpClient: OkHttpClient): TranslateApiService {
        return Retrofit.Builder()
            .baseUrl("https://translated-mymemory---translation-memory.p.rapidapi.com/get?")
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create()).build()
            .create(TranslateApiService::class.java)
    }

}