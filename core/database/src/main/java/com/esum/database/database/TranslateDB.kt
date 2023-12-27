package com.esum.database.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.esum.database.dao.CardDao
import com.esum.database.dao.ProfileDao
import com.esum.database.entity.CardEntity
import com.esum.database.entity.ProfileEntity

@Database(
    entities = [CardEntity::class , ProfileEntity::class],
    version = 1,
    exportSchema = true
)
abstract class TranslateDB : RoomDatabase() {

    abstract fun cardDao() : CardDao

    abstract fun profileDao() : ProfileDao

}