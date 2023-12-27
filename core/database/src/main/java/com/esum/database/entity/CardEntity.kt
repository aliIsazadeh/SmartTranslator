package com.esum.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Fts4
import androidx.room.PrimaryKey

@Entity(tableName = "card_table")
data class CardEntity(

    @PrimaryKey
    @ColumnInfo(name = "id")
    val id : Long,

    @ColumnInfo("define_language")
    val defineLanguage : String ?,

    @ColumnInfo("en")
    val english : String ?,

    @ColumnInfo("fa")
    val farsi : String ?,

    @ColumnInfo("tu")
    val turkish : String ?,

    @ColumnInfo("fe")
    val french : String ?,

    @ColumnInfo("sp")
    val spaniel : String ?,

    @ColumnInfo("gr")
    val germany : String ?,

    @ColumnInfo("sentence")
    val sentence : String ?,

    @ColumnInfo("image")
    val image : String ?,

    @ColumnInfo("create_date")
    val createDate : String ?,

    @ColumnInfo("update_date")
    val updateDate : String ?,

    //correct answer count in a row
    @ColumnInfo("CAC")
    val correctAnswerCount : Int = 0,

    @ColumnInfo("active")
    val active : Boolean = true,

    @ColumnInfo("desc")
    val description : String ?







    )
