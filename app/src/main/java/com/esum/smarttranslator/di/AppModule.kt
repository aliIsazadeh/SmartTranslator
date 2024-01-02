package com.esum.smarttranslator.di

import com.esum.feature.card.presentation.navigation.CardApi
import com.esum.smarttranslator.navigation.NavigationProvider
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
    fun provideNavigationProvider(cardApi: CardApi ) : NavigationProvider {
        return NavigationProvider(cardApi)
    }

}