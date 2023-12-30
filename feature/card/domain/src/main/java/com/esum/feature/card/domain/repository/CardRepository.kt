package com.esum.feature.card.domain.repository

import com.esum.common.constraints.ResultConstraints
import com.esum.database.entity.CardEntity
import kotlinx.coroutines.flow.Flow

interface CardRepository {

    fun getAllCards(): Flow<ResultConstraints<List<CardEntity>>>
    fun getCardById(id: Long): Flow<ResultConstraints<CardEntity?>>
    suspend fun insertCard(cardEntity: CardEntity): Flow<ResultConstraints<Long>>
    suspend fun updateCard(cardEntity: CardEntity) : Flow<ResultConstraints<String>>
    suspend fun deleteCardById(id: Long) : Flow<ResultConstraints<String>>
    suspend fun deleteCard(cardEntity: CardEntity) : Flow<ResultConstraints<String>>

}