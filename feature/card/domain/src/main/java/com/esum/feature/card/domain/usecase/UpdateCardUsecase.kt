package com.esum.feature.card.domain.usecase

import com.esum.common.date.getCurrentDate
import com.esum.database.entity.CardEntity
import com.esum.feature.card.domain.repository.CardRepository
import javax.inject.Inject

class UpdateCardUsecase @Inject constructor(private val cardRepository: CardRepository) {

    suspend operator fun invoke(entity: CardEntity) = cardRepository.updateCard(entity.copy(updateDate = getCurrentDate()))

}