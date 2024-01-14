package com.esum.feature.card.domain.local.usecase

import com.esum.common.constraints.ResultConstraints
import com.esum.feature.card.domain.local.model.CardWithLanguage
import com.esum.feature.card.domain.local.repository.CardRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllCardsUsecase @Inject constructor(private val cardRepository: CardRepository) {

    operator fun invoke() : Flow<ResultConstraints<List<CardWithLanguage>>> = cardRepository.getAllCards()

}