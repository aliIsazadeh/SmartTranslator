package com.esum.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.esum.database.entity.Language
import kotlinx.coroutines.flow.Flow

@Dao
interface LanguageDao {

    @Query("SELECT * FROM languages")
    fun getAllLanguages(): Flow<List<Language>>

    @Insert(onConflict = OnConflictStrategy.REPLACE, entity = Language::class)
    suspend fun insertLanguage(languageEntity: Language): Long

    @Update(entity = Language::class)
    suspend fun updateLanguage(entity: Language)

    @Query("SELECT * FROM languages WHERE id = :id")
    fun getLanguageById(id: Long): Flow<Language?>

    @Query("Delete From languages Where id = :id")
    suspend fun deleteLanguageById(id: Long)

    @Delete(entity = Language::class)
    suspend fun deleteLanguage(LanguageEntity: Language)

}