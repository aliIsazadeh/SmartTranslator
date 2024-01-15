package com.esum.feature.card.presentation.addingCard.di

import com.esum.feature.card.presentation.navigation.CardApi
import com.esum.feature.card.presentation.navigation.CardApiImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object UiModule {

    @Provides
    fun provideCardApi() : CardApi {
        return CardApiImpl()
    }

}