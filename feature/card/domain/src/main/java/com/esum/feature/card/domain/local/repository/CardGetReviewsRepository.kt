package com.esum.feature.card.domain.local.repository

import com.esum.common.constraints.ResultConstraints
import com.esum.feature.card.domain.local.model.CardWithLanguage
import kotlinx.coroutines.flow.Flow

interface CardGetReviewsRepository {
    fun getCardReviews() : Flow<ResultConstraints<List<CardWithLanguage>>>
}