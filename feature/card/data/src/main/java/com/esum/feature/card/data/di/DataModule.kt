package com.esum.feature.card.data.di

import com.esum.database.dataProvider.CardDataProvider
import com.esum.feature.card.data.repository.CardRepositoryImpl
import com.esum.feature.card.domain.repository.CardRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DataModule {
    @Provides
    @Singleton
    fun provideCardRepository(
        cardDataProvider: CardDataProvider,
    ) : CardRepository {
        return CardRepositoryImpl(cardDataProvider = cardDataProvider , dispatcher = Dispatchers.IO )
    }

}