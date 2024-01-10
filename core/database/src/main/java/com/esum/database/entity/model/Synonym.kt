package com.esum.database.entity.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


data class Synonym(
    val synonym: List<String?>
)