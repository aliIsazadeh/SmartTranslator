package com.esum.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.esum.database.entity.model.Antonym
import com.esum.database.entity.model.Synonym
import java.util.UUID

@Entity("description_definition")
data class DescriptionDefinition(
    @PrimaryKey()
    @ColumnInfo(name = "id")
    val id: UUID,
    @ColumnInfo(name = "definition") val definition: String?,
    @ColumnInfo(name = "example") val example: String?,
    @ColumnInfo(name = "synonyms")
    val synonyms: Synonym,
    @ColumnInfo(name = "antonyms")
    val antonym: Antonym,
    @ColumnInfo(name = "dm_id") val descriptionMeaningId: UUID,


    )