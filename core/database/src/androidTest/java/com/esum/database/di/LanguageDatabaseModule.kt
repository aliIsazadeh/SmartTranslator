package com.esum.database.di

import com.esum.database.dao.LanguageDao
import com.esum.database.dataProvider.languag.LanguageInsertDataProvider
import com.esum.database.dataProvider.languag.LanguageProvider
import com.esum.database.database.TranslateDB
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import javax.inject.Singleton

@TestInstallIn(components = [SingletonComponent::class] , replaces = [LanguageModule::class])
@Module
object LanguageDatabaseModule {
    @Singleton
    @Provides
    fun provideLanguageDao(db: TranslateDB): LanguageDao {
        return db.languageDao()
    }
    @Singleton
    @Provides
    fun provideLanguageProvider(dao: LanguageDao): LanguageProvider {
        return LanguageProvider(dao)
    }

    @Singleton
    @Provides
    fun provideLanguageInsertProvider(dao: LanguageDao): LanguageInsertDataProvider {
        return LanguageInsertDataProvider(dao)
    }
}