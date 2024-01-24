package com.esum.feature.card.data.local.di

import com.esum.database.dataProvider.card.CardDataProvider
import com.esum.database.dataProvider.card.CardGetReviewsDataProvider
import com.esum.database.dataProvider.card.CardInsertDataProvider
import com.esum.database.dataProvider.definition.DescriptionDefinitionInsertProvider
import com.esum.database.dataProvider.definition.DescriptionDefinitionProvider
import com.esum.database.dataProvider.description.DescriptionInsertProvider
import com.esum.database.dataProvider.meaning.DescriptionMeaningsProvider
import com.esum.database.dataProvider.description.DescriptionProvider
import com.esum.database.dataProvider.languag.LanguageInsertDataProvider
import com.esum.database.dataProvider.languag.LanguageProvider
import com.esum.database.dataProvider.meaning.DescriptionMeaningsInsertProvider
import com.esum.feature.card.data.local.repository.CardRepositoryImpl
import com.esum.feature.card.data.local.repository.CardInsertRepositoryImpl
import com.esum.feature.card.data.local.repository.CardReviewsRepositoryImpl
import com.esum.feature.card.domain.local.repository.CardGetReviewsRepository
import com.esum.feature.card.domain.local.repository.CardInsertRepository
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
            languageProvider = languageProvider,
            dispatcher = Dispatchers.IO
        )
    }

    @Provides
    @Singleton
    fun provideInsertCardRepository(
        cardInsertDataProvider: CardInsertDataProvider,
        languageInsertDataProvider: LanguageInsertDataProvider,
        descriptionInsertProvider: DescriptionInsertProvider,
        descriptionDefinitionInsertProvider: DescriptionDefinitionInsertProvider,
        descriptionMeaningsInsertProvider: DescriptionMeaningsInsertProvider
    ): CardInsertRepository {
        return CardInsertRepositoryImpl(
            dispatcher = Dispatchers.IO,
            descriptionInsertProvider = descriptionInsertProvider,
            languageProvider = languageInsertDataProvider,
            descriptionDefinitionInsertProvider = descriptionDefinitionInsertProvider,
            descriptionMeaningsInsertProvider = descriptionMeaningsInsertProvider,
            cardDataProvider = cardInsertDataProvider
        )
    }

    @Provides
    @Singleton
    fun provideCardReviewsRepository(getReviewsDataProvider: CardGetReviewsDataProvider): CardGetReviewsRepository {
        return CardReviewsRepositoryImpl(getReviewsDataProvider, Dispatchers.IO)
    }

}