package com.esum.database.di

import com.esum.database.dao.LanguageDao
import com.esum.database.dataProvider.LanguageProvider
import com.esum.database.database.TranslateDB
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object LanguageModule {

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

}