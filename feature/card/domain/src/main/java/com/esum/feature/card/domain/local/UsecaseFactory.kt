package com.esum.feature.card.domain.local

import com.esum.feature.card.domain.local.usecase.DeleteCardByIdUsecase
import com.esum.feature.card.domain.local.usecase.DeleteCardUsecase
import com.esum.feature.card.domain.local.usecase.GetAllCardsUsecase
import com.esum.feature.card.domain.local.usecase.GetCardByIdUsecase
import com.esum.feature.card.domain.local.usecase.InsertCardUsecase
import com.esum.feature.card.domain.local.usecase.UpdateCardUsecase

data class UsecaseFactory(
    val deleteCardByIdUsecase: DeleteCardByIdUsecase,
    val deleteCardUsecase: DeleteCardUsecase,
    val getAllCardsUsecase: GetAllCardsUsecase,
    val getCardByIdUsecase: GetCardByIdUsecase,
    val insertCardUsecase: InsertCardUsecase,
    val updateCardUsecase: UpdateCardUsecase
)