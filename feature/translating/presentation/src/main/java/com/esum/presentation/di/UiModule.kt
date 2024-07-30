package com.esum.presentation.di


import com.esum.presentation.navigation.TranslateApi
import com.esum.presentation.navigation.TranslationApiImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object UiModule {
    @Provides
    fun provideTranslateApi() : TranslateApi {
        return TranslationApiImpl()
    }
}