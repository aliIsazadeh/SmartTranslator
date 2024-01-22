package com.esum.feature.card.presentation.repository

import com.esum.common.constraints.ResultConstraints
import com.esum.database.entity.CardEntity
import com.esum.feature.card.domain.local.model.Card
import com.esum.feature.card.domain.local.model.CardWithLanguage
import com.esum.feature.card.domain.local.repository.CardRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import java.util.UUID

class CardRepositoryFakeImpl : CardRepository {
    override fun getAllCards(): Flow<ResultConstraints<List<CardWithLanguage>>> = flow {
        TODO("Not yet implemented")
    }

    override fun getCardById(id: UUID): Flow<ResultConstraints<CardWithLanguage?>> = flow {
        TODO("Not yet implemented")
    }

    override suspend fun insertCard(card: Card): Flow<ResultConstraints<Long>> = flow {
        TODO("Not yet implemented")
    }

    override suspend fun updateCard(cardEntity: CardWithLanguage): Flow<ResultConstraints<String>> {
        TODO("Not yet implemented")
    }

    override suspend fun deleteCardById(id: UUID): Flow<ResultConstraints<String>> = flow {
        TODO("Not yet implemented")
    }

    override suspend fun deleteCard(cardEntity: CardWithLanguage): Flow<ResultConstraints<String>> =
        flow {
            TODO("Not yet implemented")
        }


}