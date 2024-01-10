package com.esum.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.esum.database.entity.DescriptionMeanings
import kotlinx.coroutines.flow.Flow

@Dao
interface DescriptionMeaningsDao {

    @Query("SELECT * FROM description_meanings")
    fun getAllDescriptionMeanings(): Flow<List<DescriptionMeanings>>

    @Insert(onConflict = OnConflictStrategy.REPLACE, entity = DescriptionMeanings::class)
    suspend fun insertDescriptionMeaning(descriptionMeaningEntity: DescriptionMeanings): Long

    @Update(entity = DescriptionMeanings::class)
    suspend fun updateDescriptionMeaning(entity: DescriptionMeanings)

    @Query("SELECT * FROM description_meanings WHERE id = :id")
    fun getDescriptionMeaningById(id: Long): Flow<DescriptionMeanings?>

    @Query("Delete From description_meanings Where id = :id")
    suspend fun deleteDescriptionMeaningById(id: Long)

    @Delete(entity = DescriptionMeanings::class)
    suspend fun deleteDescriptionMeaning(DescriptionMeaningEntity: DescriptionMeanings)

}