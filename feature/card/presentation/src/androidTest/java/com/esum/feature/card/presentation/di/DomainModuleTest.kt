package com.esum.feature.card.presentation.di

import com.esum.feature.card.domain.local.di.DomainModule
import com.esum.feature.card.domain.local.repository.CardGetReviewsRepository
import com.esum.feature.card.domain.local.repository.CardInsertRepository
import com.esum.feature.card.domain.local.repository.CardRepository
import com.esum.feature.card.domain.local.usecase.DeleteCardByIdUsecase
import com.esum.feature.card.domain.local.usecase.DeleteCardUsecase
import com.esum.feature.card.domain.local.usecase.GetAllCardsUsecase
import com.esum.feature.card.domain.local.usecase.GetCardByIdUsecase
import com.esum.feature.card.domain.local.usecase.InsertCardUsecase
import com.esum.feature.card.domain.local.usecase.UpdateCardUsecase
import com.esum.feature.card.presentation.repository.CardInsertFakeRepository
import com.esum.feature.card.presentation.repository.CardRepositoryFakeImpl
import com.esum.feature.card.presentation.repository.CardReviewFakeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import javax.inject.Singleton

@Module
@TestInstallIn(components = [SingletonComponent::class] , replaces = [DomainModule::class])
object DomainModuleTest {

    private val repository = CardRepositoryFakeImpl()
    private val cardInsertRepository = CardInsertFakeRepository()
    private val cardGetRepository = CardReviewFakeRepository()

    @Provides
    @Singleton
    fun provideCardDataRepository() :  CardRepository {
        return repository
    }

    @Provides
    @Singleton
    fun provideCardGetReviewsRepository() :  CardGetReviewsRepository {
        return cardGetRepository
    }

    @Provides
    @Singleton
    fun provideCardInsertRepository() :  CardInsertRepository {
        return cardInsertRepository
    }


    @Provides
    @Singleton
    fun provideDeleteCardUseCase(): DeleteCardUsecase {
        return DeleteCardUsecase(repository)
    }

    @Provides
    @Singleton
    fun provideDeleteCardByIdUsecase(): DeleteCardByIdUsecase {
        return DeleteCardByIdUsecase(repository)
    }

    @Provides
    @Singleton
    fun provideGetAllCardsUsecase(): GetAllCardsUsecase {
        return GetAllCardsUsecase(repository)
    }

    @Provides
    @Singleton
    fun provideGetCardByIdUsecase(): GetCardByIdUsecase {
        return GetCardByIdUsecase(repository)
    }

    @Provides
    @Singleton
    fun provideInsertCardUsecase(): InsertCardUsecase {
        return InsertCardUsecase(cardInsertRepository)
    }

    @Provides
    @Singleton
    fun provideUpdateCardUsecase(): UpdateCardUsecase {
        return UpdateCardUsecase(repository)
    }


}