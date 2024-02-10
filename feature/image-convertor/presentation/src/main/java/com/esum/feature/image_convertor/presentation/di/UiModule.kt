package com.esum.feature.image_convertor.presentation.di

import com.esum.feature.image_convertor.presentation.navigation.TranslateApi
import com.esum.feature.image_convertor.presentation.navigation.TranslateApiImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object UiModule {
    @Provides
    fun provideTranslateApi() : TranslateApi {
        return TranslateApiImpl()
    }
}