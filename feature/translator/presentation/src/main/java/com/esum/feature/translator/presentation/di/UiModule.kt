package com.esum.feature.translator.presentation.di

import com.esum.feature.translator.presentation.navigation.TranslateApi
import com.esum.feature.translator.presentation.navigation.TranslateApiImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object UiModule {
    @Provides
    fun provideTranslateApi() : TranslateApi{
        return TranslateApiImpl()
    }
}