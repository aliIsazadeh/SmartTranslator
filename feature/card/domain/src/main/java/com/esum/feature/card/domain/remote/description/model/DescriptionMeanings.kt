package com.esum.feature.card.domain.remote.description.model

data class DescriptionMeanings(
    val partOfSpeech: String,
    val definitions: List<DescriptionDefinition>
)