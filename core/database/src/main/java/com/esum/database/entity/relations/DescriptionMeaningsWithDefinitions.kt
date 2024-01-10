package com.esum.database.entity.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.esum.database.entity.DescriptionDefinition
import com.esum.database.entity.DescriptionMeanings

data class DescriptionMeaningsWithDefinitions(
    @Embedded val meanings: DescriptionMeanings,
    @Relation(
        parentColumn = "id",
        entityColumn = "dm_id"
    )
    val definitions: List<DescriptionDefinition>
)
