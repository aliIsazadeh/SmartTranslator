package com.esum.feature.card.domain.local.usecase

import com.esum.common.constraints.ResultConstraints
import com.esum.database.entity.CardEntity
import com.esum.feature.card.domain.local.model.CardWithLanguage
import com.esum.feature.card.domain.local.repository.CardRepository
import kotlinx.coroutines.flow.Flow
import java.util.UUID
import javax.inject.Inject

class GetCardByIdUsecase @Inject constructor(private val cardRepository: CardRepository) {

    operator fun invoke(id : UUID) : Flow<ResultConstraints<CardWithLanguage?>>  = cardRepository.getCardById(id)

}