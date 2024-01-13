package com.esum.database.entity.model

import com.esum.database.entity.CardEntity
import com.esum.database.entity.Language
import com.esum.database.entity.relations.LanguageWithDescriptions

data class CardWithLanguage(
    val card: CardEntity ,
    val language: LanguageWithDescriptions?
)