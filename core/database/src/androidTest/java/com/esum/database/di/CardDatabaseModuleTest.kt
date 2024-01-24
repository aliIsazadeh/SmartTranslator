package com.esum.database.di

import com.esum.database.dao.CardDao
import com.esum.database.dataProvider.card.CardDataProvider
import com.esum.database.dataProvider.card.CardInsertDataProvider
import com.esum.database.database.TranslateDB
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import javax.inject.Singleton


@Module
@TestInstallIn(replaces = [CardDatabaseModule::class ]  , components = [SingletonComponent::class])
object CardDatabaseModuleTest {


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
    fun provideCardInsertDataProvider(dao: CardDao) : CardInsertDataProvider {
        return CardInsertDataProvider(dao)
    }

}