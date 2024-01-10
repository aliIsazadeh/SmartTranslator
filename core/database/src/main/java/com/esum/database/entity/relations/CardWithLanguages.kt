package com.esum.database.entity.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.esum.database.entity.CardEntity
import com.esum.database.entity.Language

data class CardWithLanguages(
    @Embedded val cardEntity: CardEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "card_id"
    )
    val language: List<LanguageWithDescriptions>?

)