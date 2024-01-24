package com.esum.feature.card.domain.local.usecase

import com.esum.common.constraints.ResultConstraints
import com.esum.feature.card.domain.local.model.CardWithLanguage
import com.esum.feature.card.domain.local.repository.CardGetReviewsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCardReviewsUsecase @Inject constructor(private val getCardGetReviewsRepository: CardGetReviewsRepository) {

    operator fun invoke(): Flow<ResultConstraints<List<CardWithLanguage>>> =
        getCardGetReviewsRepository.getCardReviews()

}