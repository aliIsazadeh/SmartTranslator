package com.esum.feature.card.domain.local.repository

import com.esum.common.constraints.ResultConstraints
import com.esum.database.entity.CardEntity
import com.esum.feature.card.domain.local.model.Card
import com.esum.feature.card.domain.local.model.CardWithLanguage
import kotlinx.coroutines.flow.Flow

interface CardRepository {

    fun getAllCards(): Flow<ResultConstraints<List<CardWithLanguage>>>
    fun getCardById(id: Long): Flow<ResultConstraints<CardEntity?>>
    suspend fun insertCard(card: Card): Flow<ResultConstraints<Long>>
    suspend fun updateCard(cardEntity: CardEntity) : Flow<ResultConstraints<String>>
    suspend fun deleteCardById(id: Long) : Flow<ResultConstraints<String>>
    suspend fun deleteCard(cardEntity: CardEntity) : Flow<ResultConstraints<String>>

}