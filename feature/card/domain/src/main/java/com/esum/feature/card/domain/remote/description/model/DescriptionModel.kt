package com.esum.feature.card.domain.remote.description.model

import java.util.UUID

data class DescriptionModel(
    val id : UUID ? = null,
    val phonetic: String,
    val audio: String ?,
    val meanings: List<DescriptionMeanings>?,
    val licence: String
)