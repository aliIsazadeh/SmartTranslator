package com.esum.database.entity.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.esum.database.entity.Description

data class DescriptionWithMeanings(
    @Embedded val description: Description,
    @Relation(
        parentColumn = "id",
        entityColumn = "description_id"
    )
    val meanings: List<DescriptionMeaningsWithDefinitions>?

)
