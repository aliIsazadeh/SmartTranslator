package com.esum.feature.card.data.remote.translate.di

import com.esum.feature.card.data.remote.translate.repository.RemoteRepositoryImpl
import com.esum.feature.card.domain.remote.translate.repository.RemoteRepository
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