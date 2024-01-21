package com.esum.database.entity.model

import com.esum.database.entity.CardEntity
import com.esum.database.entity.relations.LanguageWithDescriptions

data class CardWithLanguageModel(
    val card: CardEntity ,
    val language: LanguageWithDescriptions?
)