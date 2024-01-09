package com.esum.feature.card.domain.remote.description.model

data class DescriptionDefinition(
    val definition: String,
    val synonyms: List<String?>,
    val antonyms: List<String?>,
    val example : String
)
