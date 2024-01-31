package com.esum.feature.card.domain.local.di

import com.esum.database.entity.model.ActiveCardsCount
import com.esum.feature.card.domain.local.repository.CardGetReviewsRepository
import com.esum.feature.card.domain.local.repository.CardInsertRepository
import com.esum.feature.card.domain.local.repository.CardRepository
import com.esum.feature.card.domain.local.usecase.DeleteCardByIdUsecase
import com.esum.feature.card.domain.local.usecase.DeleteCardUsecase
import com.esum.feature.card.domain.local.usecase.GetActiveCardsUseCase
import com.esum.feature.card.domain.local.usecase.GetAllCardsUsecase
import com.esum.feature.card.domain.local.usecase.GetCardByIdUsecase
import com.esum.feature.card.domain.local.usecase.GetCardReviewsUsecase
import com.esum.feature.card.domain.local.usecase.InsertCardUsecase
import com.esum.feature.card.domain.local.usecase.UpdateCardUsecase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DomainModule {

    @Provides
    @Singleton
    fun provideDeleteCardUseCase(repository: CardRepository): DeleteCardUsecase {
        return DeleteCardUsecase(repository)
    }

    @Provides
    @Singleton
    fun provideDeleteCardByIdUsecase(repository: CardRepository): DeleteCardByIdUsecase {
        return DeleteCardByIdUsecase(repository)
    }

    @Provides
    @Singleton
    fun provideGetAllCardsUsecase(repository: CardRepository): GetAllCardsUsecase {
        return GetAllCardsUsecase(repository)
    }

    @Provides
    @Singleton
    fun provideGetCardByIdUsecase(repository: CardRepository): GetCardByIdUsecase {
        return GetCardByIdUsecase(repository)
    }

    @Provides
    @Singleton
    fun provideInsertCardUsecase(repository: CardInsertRepository): InsertCardUsecase {
        return InsertCardUsecase(repository)
    }

    @Provides
    @Singleton
    fun provideUpdateCardUsecase(repository: CardGetReviewsRepository): UpdateCardUsecase {
        return UpdateCardUsecase(repository)
    }

    @Provides
    @Singleton
    fun provideActiveCardsUsecase(repository: CardRepository): GetActiveCardsUseCase {
        return GetActiveCardsUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideGetReviewCardsUsecase(repository: CardGetReviewsRepository): GetCardReviewsUsecase {
        return GetCardReviewsUsecase(repository)
    }

}