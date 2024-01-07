package com.esum.feature.card.data.remote.di

import com.esum.feature.card.data.remote.repository.RemoteRepositoryImpl
import com.esum.feature.card.domain.remote.repository.RemoteRepository
import com.esum.network.dataproviders.TranslateDataProvider
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