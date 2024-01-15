package com.esum.feature.card.domain.local.usecase

import com.esum.common.date.getCurrentDate
import com.esum.database.entity.CardEntity
import com.esum.feature.card.domain.local.model.CardWithLanguage
import com.esum.feature.card.domain.local.repository.CardRepository
import javax.inject.Inject

class UpdateCardUsecase @Inject constructor(private val cardRepository: CardRepository) {

    suspend operator fun invoke(entity: CardWithLanguage) = cardRepository.updateCard(entity.copy(updateDate = getCurrentDate()))

}