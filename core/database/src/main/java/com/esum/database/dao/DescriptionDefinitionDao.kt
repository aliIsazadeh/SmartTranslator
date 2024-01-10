package com.esum.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.esum.database.entity.DescriptionDefinition
import kotlinx.coroutines.flow.Flow

@Dao
interface DescriptionDefinitionDao {

    @Query("SELECT * FROM description_definition")
    fun getAllDescriptionDefinition(): Flow<List<DescriptionDefinition>>

    @Insert(onConflict = OnConflictStrategy.REPLACE, entity = DescriptionDefinition::class)
    suspend fun insertDescriptionDefinition(descriptionDefinition: DescriptionDefinition): Long

    @Update(entity = DescriptionDefinition::class)
    suspend fun updateDescriptionDefinition(entity: DescriptionDefinition)

    @Query("SELECT * FROM description_definition WHERE id = :id")
    fun getDescriptionDefinitionById(id: Long): Flow<DescriptionDefinition?>

    @Query("Delete From description_definition Where id = :id")
    suspend fun deleteDescriptionDefinitionById(id: Long)

    @Delete(entity = DescriptionDefinition::class)
    suspend fun deleteDescriptionDefinition(descriptionDefinition: DescriptionDefinition)

}