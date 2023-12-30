package com.esum.feature.card.domain.usecase

import com.esum.common.date.getCurrentDate
import com.esum.database.entity.CardEntity
import com.esum.feature.card.domain.repository.CardRepository
import javax.inject.Inject

class InsertCardUsecase @Inject constructor(private val cardRepository: CardRepository) {

    suspend operator fun invoke(entity: CardEntity) = cardRepository.insertCard(entity.copy(createDate = getCurrentDate()))


}