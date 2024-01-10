package com.esum.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Fts4
import androidx.room.PrimaryKey
import androidx.room.Relation
import java.util.UUID

@Entity(tableName = "card_table")
data class CardEntity(

    @PrimaryKey()
    @ColumnInfo(name = "id")
    val id: UUID ,

    @ColumnInfo("define_language")
    val defineLanguage: String,


    @ColumnInfo("define_text")
    val defineText: String,

    @ColumnInfo("image")
    val image: String? = null,

    @ColumnInfo("create_date")
    val createDate: String ,

    @ColumnInfo("update_date")
    val updateDate: String? = null,


    @ColumnInfo("active")
    val active: Boolean = true,

//    @Relation(
//        parentColumn = "id",
//        entityColumn = "card_id"
//    )
//    val languages: List<Language>,
//    @Relation(
//        parentColumn = "id",
//        entityColumn = "card_id"
//    )
//    val descriptions: List<Description>

)
