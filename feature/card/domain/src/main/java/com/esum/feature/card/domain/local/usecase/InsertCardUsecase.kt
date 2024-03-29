package com.esum.feature.card.domain.local.usecase

import com.esum.common.date.getCurrentDate
import com.esum.database.entity.CardEntity
import com.esum.feature.card.domain.local.model.Card
import com.esum.feature.card.domain.local.repository.CardInsertRepository
import com.esum.feature.card.domain.local.repository.CardRepository
import javax.inject.Inject

class InsertCardUsecase @Inject constructor(private val cardInsertRepository: CardInsertRepository) {

    suspend operator fun invoke(card: Card) = cardInsertRepository.insertCard(card)


}