package com.esum.feature.translating.data.translating.di

import com.esum.feature.translating.data.translating.repository.RemoteRepositoryImpl
import com.esum.feature.translating.domain.translating.repository.RemoteRepository
import com.esum.network.translate.dataprovider.TranslateDataProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DataRemoteModule {

    @Provides
    @Singleton
    fun provideRemoteRepository(translateDataProvider: TranslateDataProvider) : RemoteRepository {
        return RemoteRepositoryImpl(translateDataProvider , dispatcher = Dispatchers.IO)
    }

}