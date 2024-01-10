package com.esum.database.di

import com.esum.database.dao.DescriptionDao
import com.esum.database.dataProvider.DescriptionProvider
import com.esum.database.database.TranslateDB
import com.esum.database.entity.Description
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

}