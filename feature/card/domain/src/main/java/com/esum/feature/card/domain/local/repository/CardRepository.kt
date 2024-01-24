package com.esum.feature.card.domain.local.repository

import com.esum.common.constraints.ResultConstraints
import com.esum.feature.card.domain.local.model.ActiveCardsCount
import com.esum.feature.card.domain.local.model.Card
import com.esum.feature.card.domain.local.model.CardWithLanguage
import kotlinx.coroutines.flow.Flow
import java.util.UUID

interface CardRepository {

    fun getAllCards(): Flow<ResultConstraints<List<CardWithLanguage>>>
    fun getCardById(id: UUID): Flow<ResultConstraints<CardWithLanguage?>>

    suspend fun updateCard(cardEntity: CardWithLanguage) : Flow<ResultConstraints<String>>
    suspend fun deleteCardById(id: UUID) : Flow<ResultConstraints<String>>
    suspend fun deleteCard(cardEntity: CardWithLanguage) : Flow<ResultConstraints<String>>
    fun getActiveCardsCount() :Flow<ResultConstraints<Pair<List<ActiveCardsCount>, Int>>>

}