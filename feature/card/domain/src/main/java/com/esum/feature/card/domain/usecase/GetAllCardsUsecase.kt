package com.esum.feature.card.domain.usecase

import com.esum.common.constraints.ResultConstraints
import com.esum.database.entity.CardEntity
import com.esum.feature.card.domain.repository.CardRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllCardsUsecase @Inject constructor(private val cardRepository: CardRepository) {

    operator fun invoke() : Flow<ResultConstraints<List<CardEntity>>> = cardRepository.getAllCards()

}