package com.esum.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(tableName = "languages")
data class Language(
    @PrimaryKey()
    @ColumnInfo(name = "id")
    val id: UUID,
    @ColumnInfo(name = "region")
    val region: String,
    @ColumnInfo(name = "value") val value: String,
    @ColumnInfo(name = "card_id") val cardId: UUID,
    @ColumnInfo("CAC")
    val correctAnswerCount: Int = 0,
    @ColumnInfo("sentence")
    val sentence: String? = null,
)