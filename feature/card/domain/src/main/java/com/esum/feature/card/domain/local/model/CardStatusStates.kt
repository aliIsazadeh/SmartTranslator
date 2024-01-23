package com.esum.feature.card.domain.local.model

import android.health.connect.datatypes.units.Percentage

data class CardStatusStates(
    val completeCardsCount: String, val completeCardsPercentage: Double,
    val activeCardsCount: String, val activeCardsPercentage: Double,
    val needToLearnCardsCount: String, val needToLearnCardsPercentage: Double,
    val allCardsCount: String, val allCardsPercentage: Double
)