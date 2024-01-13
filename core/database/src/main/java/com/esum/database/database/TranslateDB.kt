package com.esum.database.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.esum.database.convertor.RoomTypeConverters
import com.esum.database.dao.CardDao
import com.esum.database.dao.DescriptionDao
import com.esum.database.dao.DescriptionDefinitionDao
import com.esum.database.dao.DescriptionMeaningsDao
import com.esum.database.dao.LanguageDao
import com.esum.database.dao.ProfileDao
import com.esum.database.entity.CardEntity
import com.esum.database.entity.Description
import com.esum.database.entity.DescriptionDefinition
import com.esum.database.entity.DescriptionMeanings
import com.esum.database.entity.Language
import com.esum.database.entity.ProfileEntity

@TypeConverters(value = [RoomTypeConverters::class])
@Database(
    entities = [CardEntity::class , ProfileEntity::class , Description::class , DescriptionDefinition::class , DescriptionMeanings::class , Language::class],
    version = 1,
    exportSchema = true
)
abstract class TranslateDB : RoomDatabase() {

    abstract fun profileDao() : ProfileDao
    abstract fun cardDao() : CardDao
    abstract fun descriptionDefinitionDao() : DescriptionDefinitionDao
    abstract fun descriptionDao() : DescriptionDao
    abstract fun descriptionMeaningsDao() : DescriptionMeaningsDao
    abstract fun languageDao() : LanguageDao


}