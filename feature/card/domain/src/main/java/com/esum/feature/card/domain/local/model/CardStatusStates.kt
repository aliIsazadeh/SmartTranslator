package com.esum.feature.card.domain.local.model

import android.health.connect.datatypes.units.Percentage

data class CardStatusStates(
    val completeCardsCount: String = "",
    val completeCardsPercentage: Float = 0.0f,
    val activeCardsCount: String = "",
    val activeCardsPercentage:  Float = 0.0f,
    val needToLearnCardsCount: String = "",
    val needToLearnCardsPercentage:  Float = 0.0f,
    val allCardsCount: String = "",
    val allCardsPercentage:  Float = 0.0f
)