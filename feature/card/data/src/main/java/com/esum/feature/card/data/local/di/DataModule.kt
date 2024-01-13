package com.esum.feature.card.data.local.di

import com.esum.database.dataProvider.CardDataProvider
import com.esum.database.dataProvider.DescriptionDefinitionProvider
import com.esum.database.dataProvider.DescriptionMeaningsProvider
import com.esum.database.dataProvider.DescriptionProvider
import com.esum.database.dataProvider.LanguageProvider
import com.esum.feature.card.data.local.repository.CardRepositoryImpl
import com.esum.feature.card.domain.local.repository.CardRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DataModule {
    @Provides
    @Singleton
    fun provideCardRepository(
        cardDataProvider: CardDataProvider,
        languageProvider: LanguageProvider,
        descriptionProvider: DescriptionProvider,
        descriptionMeaningsProvider: DescriptionMeaningsProvider,
        descriptionDefinitionProvider: DescriptionDefinitionProvider,
    ): CardRepository {
        return CardRepositoryImpl(
            cardDataProvider = cardDataProvider,
            descriptionDefinitionProvider = descriptionDefinitionProvider,
            descriptionProvider = descriptionProvider,
            descriptionMeaningsProvider = descriptionMeaningsProvider,
            languageProvider = languageProvider,
            dispatcher = Dispatchers.IO
        )
    }

}