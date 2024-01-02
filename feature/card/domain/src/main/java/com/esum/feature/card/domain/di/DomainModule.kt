package com.esum.feature.card.domain.di

import com.esum.feature.card.domain.UsecaseFactory
import com.esum.feature.card.domain.repository.CardRepository
import com.esum.feature.card.domain.usecase.DeleteCardByIdUsecase
import com.esum.feature.card.domain.usecase.DeleteCardUsecase
import com.esum.feature.card.domain.usecase.GetAllCardsUsecase
import com.esum.feature.card.domain.usecase.GetCardByIdUsecase
import com.esum.feature.card.domain.usecase.InsertCardUsecase
import com.esum.feature.card.domain.usecase.UpdateCardUsecase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DomainModule {

//    @Provides
//    @Singleton
//    fun provideUsecaseFactory(repository: CardRepository): UsecaseFactory {
//        return UsecaseFactory(
//            deleteCardByIdUsecase = DeleteCardByIdUsecase(repository),
//            deleteCardUsecase = DeleteCardUsecase(repository),
//            getCardByIdUsecase = GetCardByIdUsecase(repository),
//            getAllCardsUsecase = GetAllCardsUsecase(repository),
//            insertCardUsecase = InsertCardUsecase(repository),
//            updateCardUsecase = UpdateCardUsecase(repository)
//        )
//
//
//    }


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
    fun provideInsertCardUsecase(repository: CardRepository): InsertCardUsecase {
        return InsertCardUsecase(repository)
    }

    @Provides
    @Singleton
    fun provideUpdateCardUsecase(repository: CardRepository): UpdateCardUsecase {
        return UpdateCardUsecase(repository)
    }

}