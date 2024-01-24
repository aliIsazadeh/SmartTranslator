package com.esum.feature.card.presentation.repository

import com.esum.common.constraints.ResultConstraints
import com.esum.feature.card.domain.local.model.Card
import com.esum.feature.card.domain.local.repository.CardInsertRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class CardInsertFakeRepository : CardInsertRepository  {
    override suspend fun insertCard(card: Card): Flow<ResultConstraints<Long>> = flow{
        TODO("Not yet implemented")
    }
}