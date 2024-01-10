package com.esum.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation
import java.util.UUID


@Entity(tableName = "description_meanings")
data class DescriptionMeanings(
    @PrimaryKey()
    @ColumnInfo(name = "id")
    val id: UUID ,
    @ColumnInfo(name = "part_of_speech") val partOfSpeech: String,
    @ColumnInfo(name = "description_id") val descriptionId: UUID,

//    @Relation(
//        parentColumn = "id",
//        entityColumn = "dm_id"
//    )
//    val definitions: List<DescriptionDefinition>,
)
