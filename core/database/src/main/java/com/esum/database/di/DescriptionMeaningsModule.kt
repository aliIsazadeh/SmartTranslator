package com.esum.database.di

import com.esum.database.dao.DescriptionMeaningsDao
import com.esum.database.dataProvider.meaning.DescriptionMeaningsInsertProvider
import com.esum.database.dataProvider.meaning.DescriptionMeaningsProvider
import com.esum.database.database.TranslateDB
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DescriptionMeaningsModule {

    @Singleton
    @Provides
    fun provideDescriptionMeaningsDao(db: TranslateDB): DescriptionMeaningsDao {
        return db.descriptionMeaningsDao()
    }
    @Singleton
    @Provides
    fun provideDescriptionMeaningsProvide(dao: DescriptionMeaningsDao): DescriptionMeaningsProvider {
        return DescriptionMeaningsProvider(dao)
    }

    @Singleton
    @Provides
    fun provideDescriptionMeaningsInsertProvider(dao: DescriptionMeaningsDao): DescriptionMeaningsInsertProvider {
        return DescriptionMeaningsInsertProvider(dao)
    }

}