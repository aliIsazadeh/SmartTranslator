package com.esum.feature.card.presentation.repository

import com.esum.common.constraints.ResultConstraints
import com.esum.database.entity.CardEntity
import com.esum.feature.card.domain.local.model.Card
import com.esum.feature.card.domain.local.repository.CardRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf

class CardRepositoryFakeImpl : CardRepository {




    override fun getAllCards(): Flow<ResultConstraints<List<CardEntity>>> = flow {
    }

    override fun getCardById(id: Long): Flow<ResultConstraints<CardEntity?>> = flow {
    }

    override suspend fun insertCard(card: Card): Flow<ResultConstraints<Long>> = flow {
    }

    override suspend fun updateCard(cardEntity: CardEntity): Flow<ResultConstraints<String>> = flow {
    }

    override suspend fun deleteCardById(id: Long): Flow<ResultConstraints<String>> = flow {
    }

    override suspend fun deleteCard(cardEntity: CardEntity): Flow<ResultConstraints<String>> = flow {
    }
}