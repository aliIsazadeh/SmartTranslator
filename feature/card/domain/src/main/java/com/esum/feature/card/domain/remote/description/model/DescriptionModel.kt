package com.esum.feature.card.domain.remote.description.model

data class DescriptionModel(
    val phonetic: String,
    val audio: String,
    val meanings: List<DescriptionMeanings>,
    val licence: String
)