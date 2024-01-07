package com.esum.network.di

import com.esum.network.NetworkModule
import com.esum.network.TranslateApiService
import com.esum.network.interceptor.TranslateInterceptor
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
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
                .addHeader("X-RapidAPI-Host", "text-translator2.p.rapidapi.com")
                .addHeader("content-type", "application/x-www-form-urlencoded")
                .build()
            it.proceed(request)
        }).build()
    }


    @Provides
    @Singleton
    fun providesTranslateApiService(okHttpClient: OkHttpClient): TranslateApiService {
         val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
        return Retrofit.Builder()
            .baseUrl("https://text-translator2.p.rapidapi.com")
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create(moshi)).build()
            .create(TranslateApiService::class.java)
    }

}