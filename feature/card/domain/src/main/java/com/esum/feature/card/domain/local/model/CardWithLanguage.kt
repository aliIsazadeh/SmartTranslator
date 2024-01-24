package com.esum.feature.card.domain.local.model

import com.esum.common.lagnuage.Languages
import java.util.UUID

data class CardWithLanguage(
    val id: UUID? = null,
    val original: String = "",
    val originalLanguage: Languages = Languages.Farsi,
    val active: Boolean = true,
    val image: String? = null,
    val updateDate: String = "",
    val createDate: String = "",
    val descriptionModel: Pair<CardDetails, Languages>? )

