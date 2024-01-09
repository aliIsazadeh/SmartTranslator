package com.esum.feature.card.domain.remote.translate.di

import com.esum.feature.card.domain.remote.translate.repository.RemoteRepository
import com.esum.feature.card.domain.remote.translate.usecase.TranslateCardUseCase
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