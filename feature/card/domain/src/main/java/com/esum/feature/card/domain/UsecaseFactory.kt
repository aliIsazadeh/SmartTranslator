package com.esum.feature.card.domain

import com.esum.feature.card.domain.usecase.DeleteCardByIdUsecase
import com.esum.feature.card.domain.usecase.DeleteCardUsecase
import com.esum.feature.card.domain.usecase.GetAllCardsUsecase
import com.esum.feature.card.domain.usecase.GetCardByIdUsecase
import com.esum.feature.card.domain.usecase.InsertCardUsecase
import com.esum.feature.card.domain.usecase.UpdateCardUsecase

data class UsecaseFactory(
    val deleteCardByIdUsecase: DeleteCardByIdUsecase ,
    val deleteCardUsecase: DeleteCardUsecase ,
    val getAllCardsUsecase: GetAllCardsUsecase ,
    val getCardByIdUsecase: GetCardByIdUsecase ,
    val insertCardUsecase: InsertCardUsecase ,
    val updateCardUsecase: UpdateCardUsecase
)