package com.esum.database.di

import com.esum.database.dao.DescriptionDefinitionDao
import com.esum.database.dataProvider.DescriptionDefinitionProvider
import com.esum.database.database.TranslateDB
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DescriptionDefinitionModule {

    @Singleton
    @Provides
    fun provideDescriptionDefinitionDao(db: TranslateDB): DescriptionDefinitionDao {
        return db.descriptionDefinitionDao()
    }
    @Singleton
    @Provides
    fun provideDescriptionDefinitionProvide(dao: DescriptionDefinitionDao): DescriptionDefinitionProvider {
        return DescriptionDefinitionProvider(dao)
    }

}