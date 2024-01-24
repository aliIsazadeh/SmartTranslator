package com.esum.database.di

import com.esum.database.dao.DescriptionDao
import com.esum.database.dataProvider.description.DescriptionInsertProvider
import com.esum.database.dataProvider.description.DescriptionProvider
import com.esum.database.database.TranslateDB
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DescriptionModule {

    @Singleton
    @Provides
    fun provideDescriptionDao(db: TranslateDB): DescriptionDao {
        return db.descriptionDao()
    }
    @Singleton
    @Provides
    fun provideDescriptionProvide(dao: DescriptionDao): DescriptionProvider {
        return DescriptionProvider(dao)
    }

    @Singleton
    @Provides
    fun provideDescriptionInsertProvider(dao: DescriptionDao): DescriptionInsertProvider {
        return DescriptionInsertProvider(dao)
    }

}