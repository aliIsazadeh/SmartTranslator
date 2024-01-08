package com.esum.feature.card.presentation.di

import com.esum.feature.card.domain.remote.di.RemoteDomainModule
import com.esum.feature.card.domain.remote.repository.RemoteRepository
import com.esum.feature.card.domain.remote.usecase.TranslateCardUseCase
import com.esum.feature.card.presentation.repository.RemoteRepositoryFakeImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import javax.inject.Singleton

@Module
@TestInstallIn(replaces = [RemoteDomainModule::class] , components = [SingletonComponent::class])
object RemoteDomainModuleTest {

    private val repository = RemoteRepositoryFakeImpl()

    @Provides
    @Singleton
    fun provideTranslateUsecase() : TranslateCardUseCase {
        return TranslateCardUseCase(repository)
    }

}