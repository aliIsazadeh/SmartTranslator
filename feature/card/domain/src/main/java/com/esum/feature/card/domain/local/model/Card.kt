package com.esum.feature.card.domain.local.model

import com.esum.common.lagnuage.Languages
import com.esum.feature.card.domain.remote.description.model.DescriptionModel
import java.util.UUID

data class Card(
    val id: UUID? = null,
    val original: String = "",
    val originalLanguage: Languages = Languages.Farsi,
    val active: Boolean = true,
    val image: String? = null,
    val updateDate: String = "",
    val createDate: String = "",
    val descriptionModel: List<Pair<CardDetails, Languages>>? = listOf()
)

data class CardDetails(
    val id: UUID? = null,
    val correctAnswerCount: Int,
    val translated: String,
    val description: DescriptionModel?,
    val sentence : String

)