package com.esum.feature.image_convertor.presentation.di

import com.esum.feature.image_convertor.presentation.navigation.ImageConvertorApi
import com.esum.feature.image_convertor.presentation.navigation.ImageConvertorApiImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object UiModule {
    @Provides
    fun provideTranslateApi() : ImageConvertorApi {
        return ImageConvertorApiImpl()
    }
}