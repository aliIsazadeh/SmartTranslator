package com.esum.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Fts4
import androidx.room.PrimaryKey

@Entity(tableName = "card_table")
data class CardEntity(

    @PrimaryKey
    @ColumnInfo(name = "id")
    val id : Long = 0L,

    @ColumnInfo("define_language")
    val defineLanguage : String ? = null,

    @ColumnInfo("en")
    val english : String ? = null,

    @ColumnInfo("fa")
    val farsi : String ? = null,

    @ColumnInfo("tu")
    val turkish : String ? = null,

    @ColumnInfo("fe")
    val french : String ? = null,

    @ColumnInfo("sp")
    val spaniel : String ? = null,

    @ColumnInfo("gr")
    val germany : String ? = null,

    @ColumnInfo("sentence")
    val sentence : String ? = null,

    @ColumnInfo("image")
    val image : String ? = null,

    @ColumnInfo("create_date")
    val createDate : String ? = null,

    @ColumnInfo("update_date")
    val updateDate : String ? = null,

    //correct answer count in a row
    @ColumnInfo("CAC")
    val correctAnswerCount : Int = 0,

    @ColumnInfo("active")
    val active : Boolean = true,

    @ColumnInfo("desc")
    val description : String ? = null







    )
