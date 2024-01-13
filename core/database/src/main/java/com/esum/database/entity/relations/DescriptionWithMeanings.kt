package com.esum.database.entity.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.esum.database.entity.Description
import com.esum.database.entity.DescriptionMeanings

data class DescriptionWithMeanings(
    @Embedded val description: Description,
    @Relation(
        entity = DescriptionMeanings::class,
        parentColumn = "id",
        entityColumn = "description_id"
    )
    val meanings: List<DescriptionMeaningsWithDefinitions>?

)
