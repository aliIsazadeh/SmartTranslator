package com.esum.feature.card.domain.remote.description.model

import java.util.UUID

data class DescriptionDefinition(
    val id: UUID? = null,
    val definition: String?,
    val synonyms: List<String?>,
    val antonyms: List<String?>,
    val example : String?
)
