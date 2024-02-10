package com.esum.feature.card.presentation.di


import com.esum.feature.card.presentation.repository.RemoteRepositoryFakeImpl
import com.esum.feature.translating.domain.translating.di.RemoteDomainModule
import com.esum.feature.translating.domain.translating.repository.RemoteRepository
import com.esum.feature.translating.domain.translating.usecase.TranslateCardUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import javax.inject.Singleton

@Module
@TestInstallIn(replaces = [RemoteDomainModule::class ] , components = [SingletonComponent::class])
object RemoteDomainModuleTest {

    private val repository = RemoteRepositoryFakeImpl()

    @Provides
    @Singleton
    fun RemoteRepositoryFake() : RemoteRepository {
        return repository
    }


    @Provides
    @Singleton
    fun provideTranslateUsecase() : TranslateCardUseCase {
        return TranslateCardUseCase(repository)
    }



}