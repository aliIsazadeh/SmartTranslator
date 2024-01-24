package com.esum.feature.card.presentation.reviewCards.state

import com.esum.common.lagnuage.Languages
import com.esum.feature.card.domain.local.model.CardWithLanguage

data class ReviewCardState(
    val cardFrontState: CardFrontState,
    val cardBackState: CardWithLanguage
)

data class CardFrontState(
    val original: String,
    val originalLanguages: Languages,
    val pronunciation: String?,
    val audio: String?,
    val example: String?,
    val click: () -> Unit
)

