package com.esum.feature.card.presentation.repository

import com.esum.common.constraints.ResultConstraints
import com.esum.feature.card.domain.local.model.CardWithLanguage
import com.esum.feature.card.domain.local.repository.CardGetReviewsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class CardReviewFakeRepository : CardGetReviewsRepository {
    override fun getCardReviews(): Flow<ResultConstraints<List<CardWithLanguage>>> = flow {
    }
}