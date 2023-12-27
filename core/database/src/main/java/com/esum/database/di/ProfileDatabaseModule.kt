package com.esum.database.di

import com.esum.database.dao.ProfileDao
import com.esum.database.dataProvider.ProfileDataProvider
import com.esum.database.database.TranslateDB
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
object ProfileDatabaseModule {
    @Singleton
    @Provides
    fun provideProfileDao(db: TranslateDB) : ProfileDao {
        return db.profileDao()
    }

    @Singleton
    @Provides
    fun provideProfileDataProvider(dao: ProfileDao) : ProfileDataProvider {
        return ProfileDataProvider(dao)
    }


}