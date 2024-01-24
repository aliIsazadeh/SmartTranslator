package com.esum.feature.card.domain.local.repository

import com.esum.common.constraints.ResultConstraints
import com.esum.feature.card.domain.local.model.Card
import kotlinx.coroutines.flow.Flow

interface CardInsertRepository {
    suspend fun insertCard(card: Card): Flow<ResultConstraints<Long>>
}