package com.esum.feature.card.domain.remote.description.di

import com.esum.feature.card.domain.remote.description.repository.DescriptionRepository
import com.esum.feature.card.domain.remote.description.usecase.GetDescriptionUsecase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DescriptionDomainModule {

    @Provides
    @Singleton
    fun provideDescriptionModule(repository: DescriptionRepository): GetDescriptionUsecase {
        return GetDescriptionUsecase(repository = repository)
    }

}