package com.esum.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.esum.database.entity.Description
import kotlinx.coroutines.flow.Flow

@Dao
interface DescriptionDao {

    @Query("SELECT * FROM descriptions")
    fun getAllDescription(): Flow<List<Description>>

    @Insert(onConflict = OnConflictStrategy.REPLACE, entity = Description::class)
    suspend fun insertDescription(description: Description): Long

    @Update(entity = Description::class)
    suspend fun updateDescription(entity: Description)

    @Query("SELECT * FROM descriptions WHERE id = :id")
    fun getDescriptionById(id: Long): Flow<Description?>

    @Query("Delete From descriptions Where id = :id")
    suspend fun deleteDescriptionById(id: Long)

    @Delete(entity = Description::class)
    suspend fun deleteDescription(description: Description)

}