package com.esum.database.entity.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.esum.database.entity.Description
import com.esum.database.entity.Language

data class LanguageWithDescriptions(
    @Embedded val language : Language ,
    @Relation(
        parentColumn = "id",
        entityColumn = "language_id"
    )
    val description: DescriptionWithMeanings?
)
