package com.esum.network

import com.esum.network.interceptor.TranslateInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideInterceptor(): Interceptor {
        return TranslateInterceptor()
    }

    @Provides
    @Singleton
    fun providesClient(interceptor: TranslateInterceptor): OkHttpClient {
        return OkHttpClient.Builder().addInterceptor(interceptor).build()
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