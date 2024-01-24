package com.esum.database.di

import com.esum.database.dao.CardDao
import com.esum.database.dataProvider.card.CardDataProvider
import com.esum.database.dataProvider.card.CardGetReviewsDataProvider
import com.esum.database.dataProvider.card.CardInsertDataProvider
import com.esum.database.database.TranslateDB
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object CardDatabaseModule {

    @Singleton
    @Provides
    fun provideCardDao(db: TranslateDB) : CardDao {
        return db.cardDao()
    }

    @Singleton
    @Provides
    fun provideCardDataProvider(dao: CardDao) : CardDataProvider {
        return CardDataProvider(dao)
    }

    @Singleton
    @Provides
    fun provideCardInsertProvider(dao: CardDao) : CardInsertDataProvider {
        return CardInsertDataProvider(dao)
    }


    @Singleton
    @Provides
    fun provideCardGetReviewsDataProvider(dao: CardDao) : CardGetReviewsDataProvider {
        return CardGetReviewsDataProvider(dao)
    }


}