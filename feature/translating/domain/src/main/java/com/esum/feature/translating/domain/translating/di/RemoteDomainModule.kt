package com.esum.feature.translating.domain.translating.di

import com.esum.feature.translating.domain.translating.repository.RemoteRepository
import com.esum.feature.translating.domain.translating.usecase.TranslateCardUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RemoteDomainModule {

    @Provides
    @Singleton
    fun provideTranslateUsecase(repository: RemoteRepository) : TranslateCardUseCase {
        return TranslateCardUseCase(repository)
    }


}