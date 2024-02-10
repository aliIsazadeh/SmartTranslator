package com.esum.feature.card.presentation.di

import com.esum.feature.card.domain.remote.description.di.DescriptionDomainModule
import com.esum.feature.card.domain.remote.description.usecase.GetDescriptionUsecase
import com.esum.feature.card.presentation.repository.DescriptionRepositoryFakeImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import javax.inject.Singleton

@Module
@TestInstallIn(replaces = [DescriptionDomainModule::class] , components = [SingletonComponent::class])
object DescriptionDomainModule {

    private val descriptionRepository = DescriptionRepositoryFakeImpl()

    @Provides
    @Singleton
    fun provideGetDescriptionUsecase() : GetDescriptionUsecase {
        return GetDescriptionUsecase(descriptionRepository)
    }

}