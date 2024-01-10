package com.esum.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation
import java.util.UUID

@Entity(tableName = "descriptions")
data class Description(
    @PrimaryKey()
    @ColumnInfo(name = "id")
    val id: UUID,
    @ColumnInfo(name = "phonetic")
    val phonetic: String,
    @ColumnInfo(name = "audio") val audio: String,
    @ColumnInfo(name = "licence") val licence: String,
    @ColumnInfo(name = "language_id") val languageId: UUID,
)