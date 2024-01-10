package com.esum.feature.card.domain.remote.description.model

import java.util.UUID

data class DescriptionMeanings(
    val id: UUID? = null,
    val partOfSpeech: String,
    val definitions: List<DescriptionDefinition>
)