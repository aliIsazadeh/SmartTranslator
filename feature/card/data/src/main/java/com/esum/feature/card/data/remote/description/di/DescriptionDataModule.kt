package com.esum.feature.card.data.remote.description.di

import com.esum.feature.card.data.remote.description.repository.DescriptionRepositoryImpl
import com.esum.feature.card.domain.remote.description.repository.DescriptionRepository
import com.esum.network.description.dataproviders.DescriptionDataProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DescriptionDataModule {

    @Provides
    @Singleton
    fun provideDescriptionRepository(dataProvider: DescriptionDataProvider) : DescriptionRepository{
        return DescriptionRepositoryImpl(dataProvider , Dispatchers.IO)
    }

}