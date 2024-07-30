package com.esum.smarttranslator.di

import com.esum.feature.card.presentation.navigation.CardApi
import com.esum.feature.image_convertor.presentation.navigation.ImageConvertorApi
import com.esum.presentation.navigation.TranslateApi
import com.esum.smarttranslator.navigation.NavigationProvider
import com.esum.smarttranslator.navigationTesting.TestApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Provides
    @Singleton
    fun provideNavigationProvider(cardApi: CardApi , testApi: TestApi , imageConvertorApi: ImageConvertorApi , translateApi: TranslateApi) : NavigationProvider {
        return NavigationProvider(cardApi , testApi , imageConvertorApi , translateApi)
    }

}